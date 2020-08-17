package com.example.virtualworkrooms.controlador;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.virtualworkrooms.modelo.Categoria;
import com.example.virtualworkrooms.modelo.Mensaje;
import com.example.virtualworkrooms.modelo.Sala;
import com.example.virtualworkrooms.persistencia.CategoriasRepositorio;
import com.example.virtualworkrooms.persistencia.MensajesRepositorio;
import com.example.virtualworkrooms.persistencia.SalasRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("controladorSalas")
public class ControladorSalasImpl implements ControladorSalas {

    @Autowired
    CategoriasRepositorio categoriasRepositorio;
    @Autowired
    SalasRepositorio salasRepositorio;
    @Autowired
    MensajesRepositorio mensajesRepositorio;

    @Override
    public List<Categoria> allCategorias() {
        return categoriasRepositorio.findAll();
    }

    @Override
    public Categoria getCategoria(String id) {
        Optional<Categoria> c = categoriasRepositorio.findById(id);
        if(!c.isPresent())
            throw new NotFoundException("Categoría no encontrada");
        return c.get();
    }

    @Override
    public Sala newSala(String nombreCat, Sala sala) {
        Categoria c = categoriasRepositorio.findByNombre();
        if(c==null)
            throw new NotFoundException("Categoría no encontrada");
        
        sala.setFecha(LocalDateTime.now());
        sala.setCategoriaNombre(nombreCat);
        return salasRepositorio.save(sala);
    }

    @Override
    public void deleteSala(String nombreCat, String id) {
        salasRepositorio.deleteById(id);
    }

    @Override
    public List<Sala> allSalas(String nombreCat) {
        return salasRepositorio.findAllByCategoriaNombre(nombreCat);
    }

    @Override
    public Sala updateSala(String nombreCat, String id, Sala sala) {
        Categoria c = categoriasRepositorio.findByNombre();
        if(c==null)
            throw new NotFoundException("Categoría no encontrada");
        Optional<Sala> s = salasRepositorio.findById(id);
        if(s.isPresent()){
            s.get().setFecha(sala.getFecha());
            s.get().setNombre(sala.getNombre());
            s.get().setCategoriaNombre(c.getNombre());
            return salasRepositorio.save(s.get());
        } else throw new NotFoundException("Sala no encontrada");
    }

    @Override
    public Sala getSala(String nombreCat, String id) throws VirtualWorkRoomsException {
        Optional<Sala> s = salasRepositorio.findById(id);
        if(!s.isPresent())
            throw new NotFoundException("Sala no encontrada");
        if(!s.get().getCategoriaNombre().equals(nombreCat))
            throw new VirtualWorkRoomsException("Categoría incorrecta");
        return s.get();
    }

    @Override
    public Mensaje newMensaje(String idSala, Mensaje msj) {
        Optional<Sala> s = salasRepositorio.findById(idSala);
        if(!s.isPresent())
            throw new NotFoundException("Sala no encontrada");
        msj.setFecha(LocalDateTime.now());
        msj.setSalaId(idSala);
        return mensajesRepositorio.save(msj);
    }

    @Override
    public List<Mensaje> getMensajes(String idSala) {
        Optional<Sala> s = salasRepositorio.findById(idSala);
        if(!s.isPresent())
            throw new NotFoundException("Sala no encontrada");
        return mensajesRepositorio.findAllBySalaId(idSala);
    }

    @Override
    public Mensaje getMensaje(String idSala, String idMsj) throws VirtualWorkRoomsException {
        Optional<Mensaje> msj = mensajesRepositorio.findById(idMsj);
        if(!msj.isPresent())
            throw new NotFoundException("Mensaje no encontrado");
        if(!msj.get().getSalaId().equals(idSala))
            throw new VirtualWorkRoomsException("Sala incorrecta");
        return msj.get();
    }

    @Override
    public Mensaje updateMensaje(String idMsj, Mensaje msj) {
        Optional<Mensaje> mensaje = mensajesRepositorio.findById(idMsj);
        if(mensaje.isPresent()){
            mensaje.get().setAutor(msj.getAutor());
            mensaje.get().setFecha(msj.getFecha());
            mensaje.get().setMedia(msj.getMedia());
            mensaje.get().setTexto(msj.getTexto());
            return mensajesRepositorio.save(mensaje.get());
        } else throw new NotFoundException("Mensaje no encontrado");
    }

    @Override
    public void deleteMensaje(String idMsj) {
        mensajesRepositorio.deleteById(idMsj);

    }
    
}
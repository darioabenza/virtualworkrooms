package com.example.virtualworkrooms.controlador;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.virtualworkrooms.modelo.Categoria;
import com.example.virtualworkrooms.modelo.Mensaje;
import com.example.virtualworkrooms.modelo.Sala;
import com.example.virtualworkrooms.modelo.Usuario;
import com.example.virtualworkrooms.persistencia.CategoriasRepositorio;
import com.example.virtualworkrooms.persistencia.MensajesRepositorio;
import com.example.virtualworkrooms.persistencia.SalasRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("controladorSalas")
public class ControladorSalasImpl implements ControladorSalas {

    @Autowired
    private CategoriasRepositorio categoriasRepositorio;
    @Autowired
    private SalasRepositorio salasRepositorio;
    @Autowired
    private MensajesRepositorio mensajesRepositorio;

    /*
     * Este catálogo mantiene en memoria el estado de todas las salas. El objetivo
     * es disminuir el número de accesos a la BD y aumentar la eficiencia con la que
     * se actualiza la lista de participantes, evitando guardar esta lista en la BD.
     */
    private Map<String, Sala> salasCatalogo;

    public ControladorSalasImpl(CategoriasRepositorio categoriasRepositorio, SalasRepositorio salasRepositorio,
            MensajesRepositorio mensajesRepositorio) {

        this.categoriasRepositorio = categoriasRepositorio;
        this.salasRepositorio = salasRepositorio;
        this.mensajesRepositorio = mensajesRepositorio;
        salasCatalogo = new HashMap<String, Sala>();
        for (Sala s : salasRepositorio.findAll()) {
            salasCatalogo.put(s.getId(), s);
        }
    }

    @Override
    public List<Categoria> allCategorias() {
        return categoriasRepositorio.findAll();
    }

    @Override
    public Categoria getCategoria(String id) {
        Optional<Categoria> c = categoriasRepositorio.findById(id);
        if (!c.isPresent())
            throw new NotFoundException("Categoría no encontrada");
        return c.get();
    }

    @Override
    public Sala newSala(String nombreCat, Sala sala) {
        Categoria c = categoriasRepositorio.findByNombre(nombreCat);
        if (c == null)
            throw new NotFoundException("Categoría no encontrada");

        sala.setFecha(LocalDateTime.now());
        sala.setCategoriaNombre(nombreCat);
        Sala salaOut = salasRepositorio.save(sala);
        salasCatalogo.put(salaOut.getId(), salaOut);
        return salaOut;
    }

    @Override
    public void deleteSala(String nombreCat, String id) {
        salasCatalogo.remove(id);
        salasRepositorio.deleteById(id);
    }

    @Override
    public List<Sala> allSalas(String nombreCat) {
        return salasCatalogo.values().stream().filter(sala -> sala.getCategoriaNombre().equals(nombreCat))
                .collect(Collectors.toList());
    }

    @Override
    public Sala updateSala(String nombreCat, String id, Sala sala) {
        Categoria c = categoriasRepositorio.findByNombre(nombreCat);
        if (c == null)
            throw new NotFoundException("Categoría no encontrada");
        Optional<Sala> s = salasRepositorio.findById(id);
        if (s.isPresent()) {
            s.get().setFecha(sala.getFecha());
            s.get().setNombre(sala.getNombre());
            s.get().setCategoriaNombre(c.getNombre());
            s.get().setMensajes(sala.getMensajes());
            salasRepositorio.save(s.get());
            // se devuelve la sala del catálogo ya que en el repositorio no se guardan los
            // usuarios
            salasCatalogo.put(id, sala);
            return sala;
        } else
            throw new NotFoundException("Sala no encontrada");
    }

    @Override
    public Sala getSala(String nombreCat, String id) throws VirtualWorkRoomsException {
        Sala s = salasCatalogo.get(id);
        if (s == null)
            throw new NotFoundException("Sala no encontrada");
        if (!s.getCategoriaNombre().equals(nombreCat))
            throw new VirtualWorkRoomsException("Categoría incorrecta");
        return s;
    }

    @Override
    public Mensaje newMensaje(String idSala, Mensaje msj) {
        // Actualizar repositorio de Mensajes y añadir mensaje a sala en repositorio y
        // catálogo

        Sala s = salasCatalogo.get(idSala);
        if (s == null)
            throw new NotFoundException("Sala no encontrada");
        msj.setFecha(LocalDateTime.now());
        msj.setSalaId(idSala);
        Optional<Sala> salaEnRepo = salasRepositorio.findById(idSala);
        if (salaEnRepo.isPresent()) {
            salaEnRepo.get().addMensaje(msj);
            salasRepositorio.save(salaEnRepo.get());
        }
        Mensaje nuevoMsj = mensajesRepositorio.save(msj);
        s.addMensaje(nuevoMsj);
        return nuevoMsj;
    }

    @Override
    public List<Mensaje> getMensajes(String idSala) {
        Sala sala = salasCatalogo.get(idSala);
        if (sala == null)
            throw new NotFoundException("Sala no encontrada");
        return sala.getMensajes();
    }

    @Override
    public Usuario newParticipante(String idSala, Usuario participante) {
        Sala sala = salasCatalogo.get(idSala);
        if (sala == null)
            throw new NotFoundException("Sala no encontrada");
        sala.addParticipante(participante);
        return participante;
    }

    @Override
    public void deleteParticipante(String idSala, String idUsuario) {
        salasCatalogo.get(idSala).deleteParticipante(idUsuario);
    }

    
}
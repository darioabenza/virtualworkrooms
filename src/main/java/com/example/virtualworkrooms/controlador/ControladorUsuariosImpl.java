package com.example.virtualworkrooms.controlador;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.virtualworkrooms.modelo.Usuario;
import com.example.virtualworkrooms.persistencia.UsuariosRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("controladorUsuarios")
public class ControladorUsuariosImpl implements ControladorUsuarios {

    @Autowired
    private UsuariosRepositorio usuariosRepositorio;

    @Override
    public Usuario registrarUsuario(Usuario u) {
        if (usuariosRepositorio.findByEmail(u.getEmail()) != null)
            throw new IllegalArgumentException("El email con el que se intenta registrar ya está en uso.");
        if (usuariosRepositorio.findByNombre(u.getNombre()) != null)
            throw new IllegalArgumentException("El nombre de usuario con el que se intenta registrar ya está en uso.");
        
        u.setFechaRegistro(LocalDateTime.now());
        Usuario usuario = usuariosRepositorio.insert(u);
        
        return usuario;
    }

    @Override
    public Usuario getUsuario(String id) {
        Optional<Usuario> u = usuariosRepositorio.findById(id);
        if(!u.isPresent())
            throw new NotFoundException("Usuario no encontrado");
        return u.get();
    }

    @Override
    public List<Usuario> getUsuarios() {
        return usuariosRepositorio.findAll();
    }

    @Override
    public Usuario updateUsuario(String id, Usuario u) {
        Optional<Usuario> usuario = usuariosRepositorio.findById(id);
        if(usuario.isPresent()){
            usuario.get().setEmail(u.getEmail());
            usuario.get().setFechaRegistro(u.getFechaRegistro());
            usuario.get().setHorasTrabajadas(u.getHorasTrabajadas());
            usuario.get().setNombre(u.getNombre());
            usuario.get().setPassword(u.getPassword());
            return usuariosRepositorio.save(usuario.get());
        } else throw new NotFoundException("Usuario no encontrado");
    }

    @Override
    public void deleteUsuario(String id) {
        usuariosRepositorio.deleteById(id);

    }
    
}
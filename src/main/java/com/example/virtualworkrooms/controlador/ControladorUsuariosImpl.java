package com.example.virtualworkrooms.controlador;

import java.time.LocalDateTime;

import com.example.virtualworkrooms.modelo.Usuario;
import com.example.virtualworkrooms.persistencia.UsuariosRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("controladorUsuarios")
public class ControladorUsuariosImpl implements ControladorUsuarios {

    @Autowired
    private UsuariosRepositorio repositorioUsuarios;

    @Override
    public Usuario registrarUsuario(Usuario u) throws VirtualWorkRoomsException{
        if(repositorioUsuarios.findByEmail(u.getEmail())!=null)
            throw new VirtualWorkRoomsException("El email con el que se intenta registrar ya está en uso.");
        if(repositorioUsuarios.findByNombre(u.getNombre())!=null)
            throw new VirtualWorkRoomsException("El nombre de usuario con el que se intenta registrar ya está en uso.");
        u.setFechaRegistro(LocalDateTime.now());
        Usuario usuario = repositorioUsuarios.insert(u);
        return usuario;
    }

    @Override
    public Usuario getUsuario(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Usuario getUsuarios() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Usuario updateUsuario(String id, Usuario u) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteUsuario(String id) {
        // TODO Auto-generated method stub

    }
    
}
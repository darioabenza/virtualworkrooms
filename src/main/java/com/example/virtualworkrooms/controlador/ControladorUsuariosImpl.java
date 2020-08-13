package com.example.virtualworkrooms.controlador;

import com.example.virtualworkrooms.modelo.Usuario;

import org.springframework.stereotype.Component;

@Component("controladorUsuarios")
public class ControladorUsuariosImpl implements ControladorUsuarios {

    @Override
    public void registrarUsuario(Usuario u) {
        // TODO Auto-generated method stub

    }

    @Override
    public Usuario getUsuario(String email, String password) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
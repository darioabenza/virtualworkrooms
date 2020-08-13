package com.example.virtualworkrooms.controlador;

import com.example.virtualworkrooms.modelo.Usuario;

public interface ControladorUsuarios {
    public void registrarUsuario(Usuario u);
    public Usuario getUsuario(String email, String password);

}
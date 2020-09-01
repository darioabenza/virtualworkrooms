package com.example.virtualworkrooms.rest.seguridad;

import com.example.virtualworkrooms.modelo.Usuario;

public class AuthenticationResponse {

    private final String jwt;
    private final Usuario usuario;


    public AuthenticationResponse(String jwt, Usuario usuario) {
        this.jwt = jwt;
        this.usuario = usuario;
    }


    public String getJwt() {
        return this.jwt;
    }


    public Usuario getUsuario() {
        return this.usuario;
    }
    
}
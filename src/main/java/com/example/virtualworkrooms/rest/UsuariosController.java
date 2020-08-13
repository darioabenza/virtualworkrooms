package com.example.virtualworkrooms.rest;

import com.example.virtualworkrooms.controlador.ControladorUsuarios;
import com.example.virtualworkrooms.modelo.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuariosController {
    @Autowired
    private ControladorUsuarios controladorUsuarios;

    @GetMapping("/users/{id}")
    public Usuario getUsuario(@PathVariable String id){
        return null;
    }

    @PostMapping("/users")
    public Usuario registrarUsuario(@RequestBody Usuario u){
        return null;
    }

}
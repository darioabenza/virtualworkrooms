package com.example.virtualworkrooms.rest;

import com.example.virtualworkrooms.controlador.ControladorCategorias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriasController {

    @Autowired
    private ControladorCategorias controladorCategorias;
}
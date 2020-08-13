package com.example.virtualworkrooms.rest;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalasController {
    @AutoWired
    private ControladorSalas controladorSalas;
}
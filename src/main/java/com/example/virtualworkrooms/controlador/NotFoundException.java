package com.example.virtualworkrooms.controlador;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException{

    public NotFoundException(String id){
        super("No se pudo encontrar el recurso con id "+ id);
    }
    
}
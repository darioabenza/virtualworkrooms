package com.example.virtualworkrooms.rest;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException{

    public NotFoundException(String id){
        super("No se pudo encontrar el recurso con id "+ id);
    }
    
}
package com.example.virtualworkrooms.persistencia;

import com.example.virtualworkrooms.modelo.Mensaje;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MensajesRepositorio extends MongoRepository<Mensaje, String> {
    
}
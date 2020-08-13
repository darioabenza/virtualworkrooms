package com.example.virtualworkrooms.persistencia;

import com.example.virtualworkrooms.modelo.Usuario;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuariosRepositorio extends MongoRepository<Usuario, String> {
    
}
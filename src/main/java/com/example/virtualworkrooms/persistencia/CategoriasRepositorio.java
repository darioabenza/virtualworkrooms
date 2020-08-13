package com.example.virtualworkrooms.persistencia;

import com.example.virtualworkrooms.modelo.Categoria;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoriasRepositorio extends MongoRepository<Categoria, String>{
}
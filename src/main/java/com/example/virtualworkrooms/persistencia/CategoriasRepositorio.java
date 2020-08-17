package com.example.virtualworkrooms.persistencia;

import com.example.virtualworkrooms.modelo.Categoria;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component("categoriasRepositorio")
public interface CategoriasRepositorio extends MongoRepository<Categoria, String>{

	Categoria findByNombre();
}
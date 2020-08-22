package com.example.virtualworkrooms.persistencia;

import java.util.List;

import com.example.virtualworkrooms.modelo.Sala;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalasRepositorio extends MongoRepository<Sala, String> {
    public List<Sala> findAllByCategoriaNombre(String categoriaNombre);
}
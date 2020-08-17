package com.example.virtualworkrooms.persistencia;

import java.util.List;

import com.example.virtualworkrooms.modelo.Sala;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component("salasRepositorio")
public interface SalasRepositorio extends MongoRepository<Sala, String> {
    public List<Sala> findAllByCategoriaNombre(String categoriaNombre);
}
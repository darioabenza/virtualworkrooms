package com.example.virtualworkrooms.persistencia;

import com.example.virtualworkrooms.modelo.Usuario;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component("usuariosRepositorio")
public interface UsuariosRepositorio extends MongoRepository<Usuario, String> {
    public Usuario findByEmail(String email);
    public Usuario findByNombre(String nombre);
}
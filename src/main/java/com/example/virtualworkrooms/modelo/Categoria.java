package com.example.virtualworkrooms.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Categoria {
    @Id
    private String id;

    private String nombre;
    private List<Sala> salas;

    public Categoria(String nombre){
        this.nombre = nombre;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Sala> getSalas() {
        return this.salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", salas='" + getSalas() + "'" +
            "}";
    }
}
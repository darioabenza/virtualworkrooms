package com.example.virtualworkrooms.modelo;

import org.springframework.data.annotation.Id;

public class Categoria {
    @Id
    private String id;

    private String nombre;

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


    @Override
    public String toString() {
        return "{" +
            " id='" + id + "'" +
            ", nombre='" + nombre + "'" +
            "}";
    }

}
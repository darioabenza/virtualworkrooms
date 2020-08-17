package com.example.virtualworkrooms.modelo;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

public class Sala {

    @Id
    private String id;
    private String nombre;
    private LocalDateTime fecha;
    private String categoriaNombre;


    public Sala(String nombre, LocalDateTime fecha, String categoriaNombre) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.categoriaNombre = categoriaNombre;
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

    public LocalDateTime getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getCategoriaNombre() {
        return this.categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }
   

    @Override
    public String toString() {
        return "{" +
            " id='" + id + "'" +
            ", nombre='" + nombre + "'" +
            ", fecha='" + fecha + "'" +
            ", categoriaNombre='" + categoriaNombre + "'" +
            "}";
    }

    
}

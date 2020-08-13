package com.example.virtualworkrooms.modelo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

public class Sala {

    @Id
    private String id;
    private String nombre;
    private List<Mensaje> mensajes;
    private LocalDateTime fecha;
    @Transient
    private List<Usuario> usuarios;


    public Sala(String nombre, List<Mensaje> mensajes, LocalDateTime fecha, List<Usuario> usuarios) {
        this.nombre = nombre;
        this.mensajes = mensajes;
        this.fecha = fecha;
        this.usuarios = usuarios;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Mensaje> getMensajes() {
        return this.mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public LocalDateTime getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public List<Usuario> getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return "{" +
            " nombre='" + nombre + "'" +
            ", mensajes='" + mensajes + "'" +
            ", fecha='" + fecha + "'" +
            ", usuarios='" + usuarios + "'" +
            "}";
    }

}

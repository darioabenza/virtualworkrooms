package com.example.virtualworkrooms.modelo;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

public class Mensaje {
    @Id
    private String id;
    private String texto;
    private Usuario autor;
    private LocalDateTime fecha;
    private String salaId;


    public Mensaje(String texto, Usuario autor, LocalDateTime fecha, String salaId) {
        this.texto = texto;
        this.autor = autor;
        this.fecha = fecha;
        this.salaId = salaId;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Usuario getAutor() {
        return this.autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public LocalDateTime getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getSalaId() {
        return this.salaId;
    }

    public void setSalaId(String salaId) {
        this.salaId = salaId;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + id + "'" +
            ", texto='" + texto + "'" +
            ", autor='" + autor + "'" +
            ", fecha='" + fecha + "'" +
            ", salaId='" + salaId + "'" +
            "}";
    }
}
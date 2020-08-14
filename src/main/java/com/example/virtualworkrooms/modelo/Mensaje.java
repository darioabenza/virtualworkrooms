package com.example.virtualworkrooms.modelo;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

public class Mensaje {
    @Id
    private String id;
    private String texto;
    private String media;
    private Usuario autor;
    private LocalDateTime fecha;


    public Mensaje(String Texto, String media, Usuario autor, LocalDateTime Fecha) {
        this.texto = Texto;
        this.media = media;
        this.autor = autor;
        this.fecha = Fecha;
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

    public void setTexto(String Texto) {
        this.texto = Texto;
    }

    public String getMedia() {
        return this.media;
    }

    public void setMedia(String media) {
        this.media = media;
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

    public void setFecha(LocalDateTime Fecha) {
        this.fecha = Fecha;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + id + "'" +
            ", Texto='" + texto + "'" +
            ", media='" + media + "'" +
            ", autor='" + autor + "'" +
            ", Fecha='" + fecha + "'" +
            "}";
    }
    
}
package com.example.virtualworkrooms.modelo;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

public class Sala {

    @Id
    private String id;
    private String nombre;
    private LocalDateTime fecha;
    private String categoriaNombre;
    private List<Mensaje> mensajes;
    @Transient
    private List<Usuario> participantes;


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
   
    public List<Mensaje> getMensajes() {
        return this.mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }


    public List<Usuario> getParticipantes() {
        return this.participantes;
    }

    public void setParticipantes(List<Usuario> participantes) {
        this.participantes = participantes;
    }

    public void addParticipante(Usuario participante){
        if(participantes == null){
            participantes = new LinkedList<Usuario>();
            participantes.add(participante);
            return;
        }
        if (participantes.stream().noneMatch(p -> p.getId().equals(participante.getId())))
            participantes.add(participante);
    }

    public void deleteParticipante(String id){
        participantes.removeIf(p->p.getId().equals(id));
    }

    public void addMensaje(Mensaje mensaje){
        if(mensajes == null){
            mensajes = new LinkedList<Mensaje>();
        }
        mensajes.add(mensaje);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + id + "'" +
            ", nombre='" + nombre + "'" +
            ", fecha='" + fecha + "'" +
            ", categoriaNombre='" + categoriaNombre + "'" +
            ", mensajes='" + mensajes + "'" +
            ", participantes='" + participantes + "'" +
            "}";
    }
}

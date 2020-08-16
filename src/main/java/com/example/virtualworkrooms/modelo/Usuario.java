package com.example.virtualworkrooms.modelo;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

public class Usuario {
    @Id
    private String id;
    private String nombre;
    private String email;
    private String password;
    private int horasTrabajadas;
    private LocalDateTime fechaRegistro;

    public Usuario(String nombre, String email, String password, int horasTrabajadas, LocalDateTime fechaRegistro) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.horasTrabajadas = horasTrabajadas;
        this.fechaRegistro = fechaRegistro;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHorasTrabajadas() {
        return this.horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public LocalDateTime getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + id + "'" +
            ", nombre='" + nombre + "'" +
            ", email='" + email + "'" +
            ", password='" + password + "'" +
            ", horasTrabajadas='" + horasTrabajadas + "'" +
            ", fechaRegistro='" + fechaRegistro + "'" +
            "}";
    }
    

}
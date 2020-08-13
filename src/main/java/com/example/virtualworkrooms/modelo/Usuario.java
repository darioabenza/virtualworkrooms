package com.example.virtualworkrooms.modelo;

import org.springframework.data.annotation.Id;

public class Usuario {
    @Id
    private String id;
    private String nombre;
    private String email;
    private String password;
    private int horasTrabajadas;


    public Usuario(String nombre, String email, String password, int horasTrabajadas) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.horasTrabajadas = horasTrabajadas;
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

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", horasTrabajadas='" + getHorasTrabajadas() + "'" +
            "}";
    }

}
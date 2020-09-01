package com.example.virtualworkrooms.modelo;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class Usuario implements UserDetails {
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

    public Usuario() {
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

    @JsonIgnore
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
        return "{" + " id='" + id + "'" + ", nombre='" + nombre + "'" + ", email='" + email + "'" + ", password='"
                + password + "'" + ", horasTrabajadas='" + horasTrabajadas + "'" + ", fechaRegistro='" + fechaRegistro
                + "'" + "}";
    }

    //userDetails Methods
    //usados por Spring Security y JWT

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.nombre;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
    

}
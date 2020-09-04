package com.example.virtualworkrooms.modelo;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    private int tiempoTrabajado; //Milisegundos
    private LocalDateTime fechaRegistro;
    private String avatar;

    public Usuario(String nombre, String email, String password, int tiempoTrabajado, LocalDateTime fechaRegistro) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.tiempoTrabajado = tiempoTrabajado;
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario() {
	}

    public void asignarAvatar(){
        File avatarDir = new File("./src/main/resources/static/media/avatar");
        int numFiles = avatarDir.listFiles().length;
        avatar = "/media/avatar/" + avatarDir.listFiles()[new Random().nextInt(numFiles)].getName();
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

    public int getTiempoTrabajado() {
        return this.tiempoTrabajado;
    }

    public void setTiempoTrabajado(int tiempoTrabajado) {
        this.tiempoTrabajado = tiempoTrabajado;
    }

    public LocalDateTime getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }


    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    @Override
    public String toString() {
        return "{" + " id='" + id + "'" + ", nombre='" + nombre + "'" + ", email='" + email + "'" + ", password='"
                + password + "'" + ", tiempoTrabajado='" + tiempoTrabajado + "'" + ", fechaRegistro='" + fechaRegistro
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
package com.example.virtualworkrooms.controlador;

import java.util.List;

import com.example.virtualworkrooms.modelo.Usuario;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface ControladorUsuarios extends UserDetailsService{
    public Usuario registrarUsuario(Usuario u) ;
    public Usuario getUsuario(String id);
	public List<Usuario> getUsuarios();
	public Usuario updateUsuario(String id, Usuario u);
	public void deleteUsuario(String id);

}
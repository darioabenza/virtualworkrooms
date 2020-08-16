package com.example.virtualworkrooms.controlador;

import com.example.virtualworkrooms.modelo.Usuario;

public interface ControladorUsuarios {
    public Usuario registrarUsuario(Usuario u) throws VirtualWorkRoomsException;
    public Usuario getUsuario(String id);
	public Usuario getUsuarios();
	public Usuario updateUsuario(String id, Usuario u);
	public void deleteUsuario(String id);

}
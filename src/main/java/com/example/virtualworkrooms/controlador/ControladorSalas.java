package com.example.virtualworkrooms.controlador;

import java.util.List;

import com.example.virtualworkrooms.modelo.Categoria;
import com.example.virtualworkrooms.modelo.Mensaje;
import com.example.virtualworkrooms.modelo.Sala;
import com.example.virtualworkrooms.modelo.Usuario;

public interface ControladorSalas {

	List<Categoria> allCategorias();

	Categoria getCategoria(String id);

	Sala newSala(String nombreCat, Sala sala);

	void deleteSala(String nombreCat, String id);

	List<Sala> allSalas(String nombreCat);

	Sala updateSala(String nombreCat, String id, Sala sala);

	Sala getSala(String nombreCat, String id) throws VirtualWorkRoomsException;

	Mensaje newMensaje(String idSala, Mensaje mensaje);

	List<Mensaje> getMensajes(String idSala);

	Usuario newParticipante(String idSala, Usuario participante);

	void deleteParticipante(String idSala, String idUsuario);
    
}
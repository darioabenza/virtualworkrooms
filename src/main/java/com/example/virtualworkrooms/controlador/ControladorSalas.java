package com.example.virtualworkrooms.controlador;

import java.util.List;

import com.example.virtualworkrooms.modelo.Categoria;
import com.example.virtualworkrooms.modelo.Mensaje;
import com.example.virtualworkrooms.modelo.Sala;

public interface ControladorSalas {

	List<Categoria> allCategorias();

	Categoria getCategoria(String id);

	Sala newSala(String nombreCat, Sala sala);

	void deleteSala(String id, String id2);

	List<Sala> allSalas(String nombreCat);

	Sala updateSala(String id, String id2, Sala sala);

	Sala getSala(String nombreCat, String id);

	Mensaje newMensaje(String nombreCat, String id, Mensaje msj);

	List<Mensaje> getMensajes(String nombreCat, String id);

	Mensaje getMensaje(String nombreCat, String id, String idMsj);

	Mensaje updateMensaje(String nombreCat, String id, String idMsj);

	void deleteMensaje(String nombreCat, String id, String idMsj);
    
}
package com.example.virtualworkrooms.controlador;

import java.util.List;

import com.example.virtualworkrooms.modelo.Categoria;
import com.example.virtualworkrooms.modelo.Mensaje;
import com.example.virtualworkrooms.modelo.Sala;

import org.springframework.stereotype.Component;

@Component("controladorSalas")
public class ControladorSalasImpl implements ControladorSalas {

    @Override
    public List<Categoria> allCategorias() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Categoria getCategoria(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Sala newSala(String nombreCat, Sala sala) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteSala(String id, String id2) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Sala> allSalas(String nombreCat) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Sala updateSala(String id, String id2, Sala sala) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Sala getSala(String nombreCat, String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mensaje newMensaje(String nombreCat, String id, Mensaje msj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Mensaje> getMensajes(String nombreCat, String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mensaje getMensaje(String nombreCat, String id, String idMsj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mensaje updateMensaje(String nombreCat, String id, String idMsj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteMensaje(String nombreCat, String id, String idMsj) {
        // TODO Auto-generated method stub

    }
    
}
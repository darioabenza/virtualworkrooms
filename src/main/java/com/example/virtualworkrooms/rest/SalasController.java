package com.example.virtualworkrooms.rest;

import java.net.URI;
import java.util.List;

import com.example.virtualworkrooms.controlador.ControladorSalas;
import com.example.virtualworkrooms.modelo.Categoria;
import com.example.virtualworkrooms.modelo.Mensaje;
import com.example.virtualworkrooms.modelo.Sala;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class SalasController {

    @Autowired
    private ControladorSalas controladorSalas;
    

    @GetMapping("/categorias")
    public List<Categoria> allCategorias(){
        return controladorSalas.allCategorias();
    }

    @GetMapping("/categorias/{nombreCat}")
    public Categoria one(@PathVariable String nombreCat){
        return controladorSalas.getCategoria(nombreCat);
    }

    @PostMapping("/categorias/{nombreCat}/salas")
    public ResponseEntity<?> newSala(@PathVariable String nombreCat, @RequestBody Sala sala) {
        
        Sala s = controladorSalas.newSala(nombreCat, sala);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(s.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/categorias/{nombreCat}/salas")
    public List<Sala> all(@PathVariable String nombreCat){
        return controladorSalas.allSalas(nombreCat);
    }

    @GetMapping("/categorias/{nombreCat}/salas/{id}")
    public Sala one(@PathVariable String nombreCat, @PathVariable String id){
        return controladorSalas.getSala(nombreCat, id);
    }
   
    @PutMapping("/categorias/{nombreCat}/salas/{id}")
    public Sala updateSala(@PathVariable String nombreCat, @PathVariable String id, @RequestBody Sala sala){
        return controladorSalas.updateSala(nombreCat, id, sala);
    }

    @DeleteMapping("/categorias/{nombreCat}/salas/{id}")
    public void deleteSala(@PathVariable String nombreCat, @PathVariable String id){
        controladorSalas.deleteSala(nombreCat, id);
    }

    @PostMapping("/categorias/{nombreCat}/salas/{id}/mensajes")
    public ResponseEntity<?> newMensaje(@PathVariable String nombreCat, @PathVariable String id, @RequestBody Mensaje msj){
        Mensaje mensaje = controladorSalas.newMensaje(nombreCat, id, msj);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(mensaje.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/categorias/{nombreCat}/salas/{id}/mensajes")
    public List<Mensaje> allMensajes(@PathVariable String nombreCat, @PathVariable String id){
        return controladorSalas.getMensajes(nombreCat, id);
    }

    @GetMapping("/categorias/{nombreCat}/salas/{id}/mensajes/{idMsj}")
    public Mensaje oneMensaje(@PathVariable String nombreCat, @PathVariable String id, @PathVariable String idMsj){
        return controladorSalas.getMensaje(nombreCat, id, idMsj);
    }

    @PutMapping("/categorias/{nombreCat}/salas/{id}/mensajes/{idMsj}")
    public Mensaje updateMensaje(@PathVariable String nombreCat, @PathVariable String id, @PathVariable String idMsj){
        return controladorSalas.updateMensaje(nombreCat, id, idMsj);
    }

    @PutMapping("/categorias/{nombreCat}/salas/{id}/mensajes/{idMsj}")
    public void deleteMensaje(@PathVariable String nombreCat, @PathVariable String id, @PathVariable String idMsj){
        controladorSalas.deleteMensaje(nombreCat, id, idMsj);
    }
}
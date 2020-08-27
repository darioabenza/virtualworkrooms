package com.example.virtualworkrooms.rest;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.example.virtualworkrooms.controlador.ControladorSalas;
import com.example.virtualworkrooms.controlador.VirtualWorkRoomsException;
import com.example.virtualworkrooms.modelo.Categoria;
import com.example.virtualworkrooms.modelo.Mensaje;
import com.example.virtualworkrooms.modelo.Sala;
import com.example.virtualworkrooms.modelo.Usuario;

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
    public List<Categoria> allCategorias() {
        return controladorSalas.allCategorias();
    }

    @GetMapping("/categorias/{nombreCat}")
    public Categoria one(@PathVariable String nombreCat) {
        nombreCat = URLDecoder.decode(nombreCat, StandardCharsets.UTF_8);
        return controladorSalas.getCategoria(nombreCat);
    }

    @PostMapping("/categorias/{nombreCat}/salas")
    public ResponseEntity<?> newSala(@PathVariable String nombreCat, @RequestBody Sala sala)
            throws VirtualWorkRoomsException {
        
        nombreCat = URLDecoder.decode(nombreCat, StandardCharsets.UTF_8);
        Sala s = controladorSalas.newSala(nombreCat, sala);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(s.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/categorias/{nombreCat}/salas")
    public List<Sala> all(@PathVariable String nombreCat){
        nombreCat = URLDecoder.decode(nombreCat, StandardCharsets.UTF_8);
        return controladorSalas.allSalas(nombreCat);
    }

    @GetMapping("/categorias/{nombreCat}/salas/{id}")
    public Sala one(@PathVariable String nombreCat, @PathVariable String id) throws VirtualWorkRoomsException {
        nombreCat = URLDecoder.decode(nombreCat, StandardCharsets.UTF_8);
        return controladorSalas.getSala(nombreCat, id);
    }
   
    @PutMapping("/categorias/{nombreCat}/salas/{id}")
    public Sala updateSala(@PathVariable String nombreCat, @PathVariable String id, @RequestBody Sala sala){
        nombreCat = URLDecoder.decode(nombreCat, StandardCharsets.UTF_8);
        return controladorSalas.updateSala(nombreCat, id, sala);
    }

    @DeleteMapping("/categorias/{nombreCat}/salas/{id}")
    public void deleteSala(@PathVariable String nombreCat, @PathVariable String id){
        nombreCat = URLDecoder.decode(nombreCat, StandardCharsets.UTF_8);
        controladorSalas.deleteSala(nombreCat, id);
    }

    @PostMapping("/categorias/{nombreCat}/salas/{id}/mensajes")
    public ResponseEntity<?> newMensaje(@PathVariable String id, @RequestBody Mensaje msj){
        Mensaje mensaje = controladorSalas.newMensaje(id, msj);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(mensaje.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/categorias/{nombreCat}/salas/{id}/mensajes")
    public List<Mensaje> allMensajes(@PathVariable String id){
        return controladorSalas.getMensajes(id);
    }
    //TODO
/*
    @PostMapping("/categorias/{nombreCat}/salas/{id}/participantes")
    public ResponseEntity<?> newParticipante(@PathVariable String id, @RequestBody Usuario participante){
        
    }
    */
}
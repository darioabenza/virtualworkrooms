package com.example.virtualworkrooms.rest;

import java.net.URI;
import java.util.List;

import com.example.virtualworkrooms.controlador.ControladorUsuarios;
import com.example.virtualworkrooms.modelo.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UsuariosController {
    @Autowired
    private ControladorUsuarios controladorUsuarios;

    @PostMapping("/usuarios")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario u) {
        Usuario usuario = controladorUsuarios.registrarUsuario(u);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/usuarios")
    public List<Usuario> all(){
        return controladorUsuarios.getUsuarios();
    }

    @GetMapping("/usuarios/{id}")
    public Usuario one(@PathVariable String id){
        return controladorUsuarios.getUsuario(id);
    }

    @PutMapping("/usuarios/{id}")
    public Usuario updateUsuario(@PathVariable String id, Usuario u){
        return controladorUsuarios.updateUsuario(id, u);
    }

    @DeleteMapping("/usuarios/{id}")
    public void deleteUsuario(@PathVariable String id){
        controladorUsuarios.deleteUsuario(id);
    }
}
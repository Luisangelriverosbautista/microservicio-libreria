package com.jessica.usuario.controller;

import com.jessica.usuario.model.Usuario;
import com.jessica.usuario.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    private UsuarioServices usuarioServices;

    @GetMapping("/traer-usuarios")
    public List<Usuario> traesrUsuarios(){
        return usuarioServices.listarUsuarios();
    }

    @GetMapping("/traer-usuario/{id}")
    public ResponseEntity<Usuario> traesUsuario(@PathVariable Long id){
        Optional<Usuario> usuario=usuarioServices.obtenerUsuario(id);
        return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/insertar-usuario")
    public Usuario insertarUsuario(@RequestBody Usuario usuario){
        return usuarioServices.crearUsuario(usuario);
    }

    @PutMapping("/editar-usuario/{id}")
    public  ResponseEntity<Usuario> ediutarUsuario(@PathVariable Long id,  @RequestBody Usuario usuario){
        Optional<Usuario> usuarioActualizado=usuarioServices.actualizarUsuario(id,usuario);
        return usuarioActualizado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());


    }

    @DeleteMapping("/eliminar-usuario/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
        usuarioServices.eliminarUsuario(id);
        return ResponseEntity.ok().build();

    }


}

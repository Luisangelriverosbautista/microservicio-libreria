package com.jessica.libro.controller;
import com.jessica.libro.model.Autor;
import com.jessica.libro.services.AutorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autores")
@CrossOrigin(origins = "*")
public class AutorController {
    @Autowired
    private AutorService autorService;

    @GetMapping("/traer-autores")
    public List<Autor> traerAutores() {
        return autorService.obtenerTodos();
    }

    @GetMapping("/traer-autor/{id}")
    public ResponseEntity<Autor> traerUnAutor(@PathVariable Long id) {
        Optional<Autor> autor = autorService.obtenerPorId(id);
        return autor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/insertar-autores")
    public Autor insertarAutor(@RequestBody Autor autor) {
        return autorService.guardarAutor(autor);
    }

    @PutMapping("/editar-autores/{id}")
    public ResponseEntity<Autor> actualizarAutor(@PathVariable Long id, @RequestBody Autor autor) {
        Optional<Autor> actualizado = autorService.actualizarAutor(id, autor);
        return actualizado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar-autores/{id}")
    public ResponseEntity<Void> eliminarAutor(@PathVariable Long id) {
        autorService.eliminarAutor(id);
        return ResponseEntity.ok().build();
    }
}

package com.jessica.libro.controller;

import com.jessica.libro.dto.LibroDTO;
import com.jessica.libro.model.Libro;
import com.jessica.libro.services.LibroServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/libros")
@CrossOrigin(origins = "*")
public class LibroController {
    @Autowired
    private LibroServices libroServices;

    @GetMapping("/traer-libros")
    public List<LibroDTO> traerLibros() {
        return libroServices.obtenerTodos();
    }

    @GetMapping("/traer-libro/{id}")
    public ResponseEntity<LibroDTO> traerUnLibro(@PathVariable Long id) {
        return libroServices.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/insertar-libro")
    public Libro insertarLibro(@RequestBody Libro libro) {
        return libroServices.crearLibro(libro);
    }

    @PutMapping("/editar-libro/{id}")
    public ResponseEntity<Libro> actualizarlibro(@PathVariable Long id, @RequestBody Libro libro) {
        return libroServices.editarLibro(id, libro)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar-libro/{id}")
    public ResponseEntity<Void> eliminarlibro(@PathVariable Long id) {
        libroServices.eliminarLibro(id);
        return ResponseEntity.ok().build();
    }
}

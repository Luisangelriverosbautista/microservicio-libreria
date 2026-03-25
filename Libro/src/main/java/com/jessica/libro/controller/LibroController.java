package com.jessica.libro.controller;


import com.jessica.libro.DTO.LibroDTO;
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

    // Listar todos los libros en formato DTO
    @GetMapping("/traer-libros")
    public List<LibroDTO> traerLibros() {
        return libroServices.obtenerTodos()
                .stream()
                .map(LibroDTO::new) // transforma cada entidad en DTO
                .toList();
    }

    // Traer un libro por ID en formato DTO
    @GetMapping("/traer-libro/{id}")
    public ResponseEntity<LibroDTO> traerUnLibro(@PathVariable Long id) {
        return libroServices.obtenerPorId(id)
                .map(LibroDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/insertar-libro")
    public LibroDTO insertarLibro(@RequestBody Libro libro) {
        Libro nuevo = libroServices.crearLibro(libro);
        return new LibroDTO(nuevo);
    }


    // Editar un libro
    @PutMapping("/editar-libro/{id}")
    public ResponseEntity<LibroDTO> actualizarlibro(@PathVariable Long id, @RequestBody Libro libro) {
        return libroServices.editarLibro(id, libro)
                .map(LibroDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/actualizar-disponibilidad/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<LibroDTO> actualizarDisponibilidad(@PathVariable Long id, @RequestParam Boolean disponible) {
        return libroServices.actualizarDisponibilidad(id, disponible)
                .map(LibroDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un libro
    @DeleteMapping("/eliminar-libro/{id}")
    public ResponseEntity<Void> eliminarlibro(@PathVariable Long id) {
        libroServices.eliminarLibro(id);
        return ResponseEntity.ok().build();
    }
}


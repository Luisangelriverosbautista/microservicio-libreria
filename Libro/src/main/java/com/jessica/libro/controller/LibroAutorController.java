package com.jessica.libro.controller;

import com.jessica.libro.model.LibroAutor;
import com.jessica.libro.services.LibroAutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libro-autores")
public class LibroAutorController {

    @Autowired
    private LibroAutorService libroAutorService;

    // GET: listar todas las relaciones
    @GetMapping("traer-libroAutor")
    public List<LibroAutor> traerRelaciones() {
        return libroAutorService.obtenerTodos();
    }

    // GET: obtener autores de un libro específico
    @GetMapping("/libro-autor/{Id}")
    public List<LibroAutor> traerAutoresDeLibro(@PathVariable Long libroId) {
        return libroAutorService.obtenerPorLibro(libroId);
    }

    // POST: crear relación libro-autor
    @PostMapping("/insertar")
    public LibroAutor insertarRelacion(@RequestBody LibroAutor relacion) {
        return libroAutorService.crearRelacion(relacion);
    }

    // DELETE: eliminar relación libro-autor
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarRelacion(@PathVariable Long id) {
        libroAutorService.eliminarRelacion(id);
        return ResponseEntity.ok().build();
    }
}
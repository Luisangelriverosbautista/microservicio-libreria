package com.jessica.libro.controller;
import com.jessica.libro.model.Categoria;
import com.jessica.libro.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/traer-categorias")
    public List<Categoria> traerCategorias() {
        return categoriaService.obtenerTodos();
    }

    @GetMapping("/traer-categoria/{id}")
    public ResponseEntity<Categoria> traerUnaCategoria(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaService.obtenerPorId(id);
        return categoria.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/insertar-categorias")
    public Categoria insertarCategoria(@RequestBody Categoria categoria) {
        return categoriaService.guardarCategoria(categoria);
    }

    @PutMapping("/editar-categorias/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        Optional<Categoria> actualizado = categoriaService.actualizarCategoria(id, categoria);
        return actualizado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar-categorias/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok().build();
    }
}

package com.jessica.libro.services;
import com.jessica.libro.model.Categoria;
import com.jessica.libro.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServices {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> obtenerTodos() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> obtenerPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> actualizarCategoria(Long id, Categoria categoriaDetalles) {
        return categoriaRepository.findById(id).map(categoriaExistente -> {
            categoriaExistente.setNombre(categoriaDetalles.getNombre());
            return categoriaRepository.save(categoriaExistente);
        });
    }

    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}


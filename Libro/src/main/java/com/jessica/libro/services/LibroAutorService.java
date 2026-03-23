package com.jessica.libro.services;

import com.jessica.libro.model.LibroAutor;
import com.jessica.libro.repository.LibroAutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroAutorService {

    @Autowired
    private LibroAutorRepository libroAutorRepository;

    // Obtener todas las relaciones libro-autor
    public List<LibroAutor> obtenerTodos() {
        return libroAutorRepository.findAll();
    }

    // Obtener autores asociados a un libro
    public List<LibroAutor> obtenerPorLibro(Long libroId) {
        return libroAutorRepository.findByLibroId(libroId);
    }

    // Crear relación libro-autor
    public LibroAutor crearRelacion(LibroAutor relacion) {
        return libroAutorRepository.save(relacion);
    }

    // Eliminar relación libro-autor
    public void eliminarRelacion(Long id) {
        libroAutorRepository.deleteById(id);
    }
}
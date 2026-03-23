package com.jessica.libro.services;


import com.jessica.libro.model.Libro;
import com.jessica.libro.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServices {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    public Optional<Libro> obtenerPorId(Long id) {
        return libroRepository.findById(id);
    }

    public Libro crearLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public Optional<Libro> editarLibro(Long id, Libro datosActualizados) {
        return libroRepository.findById(id).map(libroExistente -> {
            libroExistente.setTitulo(datosActualizados.getTitulo());
            libroExistente.setEditorial(datosActualizados.getEditorial());
            libroExistente.setAñoPublicacion(datosActualizados.getAñoPublicacion());
            libroExistente.setCategoria(datosActualizados.getCategoria());
            libroExistente.setAutores(datosActualizados.getAutores());
            libroExistente.setDisponibilidad(datosActualizados.getDisponibilidad());
            return libroRepository.save(libroExistente);
        });
    }

    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }
}

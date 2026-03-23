package com.jessica.libro.services;

import com.jessica.libro.client.AutorClient;
import com.jessica.libro.client.CategoriaClient;
import com.jessica.libro.dto.Autor;
import com.jessica.libro.dto.Categoria;
import com.jessica.libro.dto.LibroDTO;
import com.jessica.libro.model.Libro;
import com.jessica.libro.model.LibroAutor;
import com.jessica.libro.repository.LibroAutorRepository;
import com.jessica.libro.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroServices {

    @Autowired private LibroRepository libroRepository;
    @Autowired private LibroAutorRepository libroAutorRepository;
    @Autowired private AutorClient autorClient;
    @Autowired private CategoriaClient categoriaClient;

    public List<LibroDTO> obtenerTodos() {
        return libroRepository.findAll().stream().map(libro -> {
            Categoria categoria = categoriaClient.getCategoriaById(libro.getCategoriaId());
            List<Autor> autores = obtenerAutoresDeLibro(libro.getId());
            return new LibroDTO(libro, categoria, autores);
        }).toList();
    }

    public Optional<LibroDTO> obtenerPorId(Long id) {
        return libroRepository.findById(id).map(libro -> {
            Categoria categoria = categoriaClient.getCategoriaById(libro.getCategoriaId());
            List<Autor> autores = obtenerAutoresDeLibro(libro.getId());
            return new LibroDTO(libro, categoria, autores);
        });
    }

    public Libro crearLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public Optional<Libro> editarLibro(Long id, Libro datosActualizados) {
        return libroRepository.findById(id).map(libroExistente -> {
            libroExistente.setTitulo(datosActualizados.getTitulo());
            libroExistente.setEditorial(datosActualizados.getEditorial());
            libroExistente.setAñoPublicacion(datosActualizados.getAñoPublicacion());
            libroExistente.setCategoriaId(datosActualizados.getCategoriaId());
            libroExistente.setDisponibilidad(datosActualizados.getDisponibilidad());
            return libroRepository.save(libroExistente);
        });
    }

    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }

    private List<Autor> obtenerAutoresDeLibro(Long libroId) {
        List<LibroAutor> relaciones = libroAutorRepository.findByLibroId(libroId);
        return relaciones.stream()
                .map(rel -> autorClient.getAutorById(rel.getAutorId()))
                .toList();
    }
}

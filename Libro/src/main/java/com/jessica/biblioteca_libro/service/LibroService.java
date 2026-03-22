package com.jessica.biblioteca_libro.service;

import com.jessica.biblioteca_libro.repository.LibroRepository;
import  com.jessica.biblioteca_libro.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.QuadCurve2D;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;


    public List<Libro> ObtenerTodos (){
        return libroRepository.findAll();
    }

    public Optional<Libro> ObtenerPorId (long id){
        return libroRepository.findById( id);

    }
    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public Optional<Libro> actualizarLibro(Long id, Libro libroDetalle) {
        return libroRepository.findById(id).map(libroExistente -> {
           libroExistente.setTitulo(libroDetalle.getTitulo());
           libroExistente.setAutorId(libroDetalle.getAutorId());
           libroExistente.setAño(libroDetalle.getAño());
           libroExistente.setEditorial(libroDetalle.getEditorial());
           libroExistente.setCategoriaId(libroDetalle.getCategoriaId());
           libroExistente.setDisponibilidad(libroDetalle.getDisponibilidad());
            return libroRepository.save(libroExistente);
        });
    }

    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }

}

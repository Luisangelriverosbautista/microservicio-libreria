package com.jessica.libro.services;

import com.jessica.libro.model.Libro;
import com.jessica.libro.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServices {
    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> Obtnertodos(){
        return libroRepository.findAll();

    }
    public Optional<Libro> obtenerPorId(Long id){
        return libroRepository.findById(id);
    }

    public Libro crearLibro (Libro libro){
        return libroRepository.save(libro);

    }

    public Optional<Libro> editaLibro(Long id, Libro datosActualizados) {
        return libroRepository.findById(id).map(libroExistente -> {
            // Actualizamos solo los campos necesarios
            libroExistente.setTitulo(datosActualizados.getTitulo());
            libroExistente.setAutorId(datosActualizados.getAutorId());
            libroExistente.setEditorial(datosActualizados.getEditorial());
            libroExistente.setAño(datosActualizados.getAño());
            libroExistente.setCategoriaId(datosActualizados.getCategoriaId());
            libroExistente.setDisponobilidad(datosActualizados.getDisponobilidad());
            // Guardamos cambios
            return libroRepository.save(libroExistente);
        });
    }
    public void eliminarLibro (Long id){
        libroRepository.deleteById(id);

    }
}

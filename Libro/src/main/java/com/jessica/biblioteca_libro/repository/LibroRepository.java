package com.jessica.biblioteca_libro.repository;

import com.jessica.biblioteca_libro.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByAutorId(Long autorId);
    List<Libro> findByCategoria(String categoria);

}

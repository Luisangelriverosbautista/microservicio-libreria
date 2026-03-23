package com.jessica.libro.repository;

import com.jessica.libro.model.LibroAutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroAutorRepository extends JpaRepository<LibroAutor, Long> {
    List<LibroAutor> findByLibroId(Long libroId);
}

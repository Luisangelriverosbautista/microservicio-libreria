package com.jessica.libro.repository;

import com.jessica.libro.model.Libro;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {


}

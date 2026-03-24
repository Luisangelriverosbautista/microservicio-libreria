package com.jessica.libro.DTO;

import com.jessica.libro.model.Autor;
import com.jessica.libro.model.Libro;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibroDTO {
    private Long idLibro;
    private String titulo;
    private String editorial;
    private Integer añoPublicacion;
    private Boolean disponibilidad;
    private String categoria;
    private List<String> autores;

    // Constructor que transforma la entidad Libro en DTO
    public LibroDTO(Libro libro) {
        this.idLibro = libro.getIdLibro();
        this.titulo = libro.getTitulo();
        this.editorial = libro.getEditorial();
        this.añoPublicacion = libro.getAñoPublicacion();
        this.disponibilidad = libro.getDisponibilidad();
        // Evita NullPointer si no hay categoría
        this.categoria= libro.getCategoria() != null ? libro.getCategoria().getNombre() : null;
        // Evita NullPointer si no hay autores
        this.autores = libro.getAutores() != null
                ? libro.getAutores().stream()
                .map(Autor::getNombre)
                .toList()
                : List.of();
    }
}

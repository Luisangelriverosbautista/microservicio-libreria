package com.jessica.libro.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.jessica.libro.model.Libro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonPropertyOrder({ "id", "titulo", "autor", "editorial", "año", "categoria", "disponibilidad" })
@Getter
@Setter
@AllArgsConstructor
public class LibroDTO {
    private Long id;
    private String titulo;
    private String editorial;
    private String añoPublicacion;
    private Boolean disponibilidad;   // ahora es Boolean
    private Categoria categoria;
    private List<Autor> autores;

    public LibroDTO(Libro libro, Categoria categoria, List<Autor> autores) {
        this.id = libro.getId();
        this.titulo = libro.getTitulo();
        this.editorial = libro.getEditorial();
        this.añoPublicacion = libro.getAñoPublicacion();
        this.disponibilidad = libro.getDisponibilidad(); // correcto ahora
        this.categoria = categoria;
        this.autores = autores;
    }
}

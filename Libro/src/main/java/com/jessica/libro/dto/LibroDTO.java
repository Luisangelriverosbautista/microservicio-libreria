package com.jessica.libro.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.jessica.libro.model.Libro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({ "id", "titulo", "autor", "editorial", "año", "categoria", "disponibilidad" })
@Getter
@Setter
@AllArgsConstructor
public class LibroDTO {
    private Long id;
    private String titulo;
    private Autor autor;
    private String editorial;
    private String año;
    private Categoria categoria;
    private String disponibilidad;



    public LibroDTO(Libro libro, Autor autor, Categoria categoria) {
        this.id = libro.getId();
        this.titulo = libro.getTitulo();
        this.autor = autor;
        this.editorial = libro.getEditorial();
        this.año = libro.getAño();
        this.categoria = categoria;
        this.disponibilidad = libro.getDisponibilidad();


}
}

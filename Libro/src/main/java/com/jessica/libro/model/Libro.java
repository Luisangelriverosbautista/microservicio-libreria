package com.jessica.libro.model;

import com.jessica.libro.model.Autor;
import com.jessica.libro.model.Categoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibro;

    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;

  //Relacion de muchos a muchos con autor, un autor puede tener varios libros y un libro puede tener varios autores
    @ManyToMany
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name="id_libro"),
            inverseJoinColumns = @JoinColumn(name = "id_autor")
    )
    private List<Autor> autores;


    @NotBlank(message = "La editorial es obligatoria")
    private  String editorial;

    @NotNull(message = "El año es obligatoria")
    private  Integer añoPublicacion;

   //relacion de libro con categoria, un libro solo tiene una categoria y una categoria puede tener muchos libros
    @ManyToOne
    @JoinColumn(name ="id_categoria")
    private Categoria categoria;

    @NotNull
    private  Boolean disponibilidad;

}

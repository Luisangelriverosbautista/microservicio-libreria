package com.jessica.libro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;

    private Long autorId;//relacion con la clase autor

    @NotBlank(message = "La editorial es obligatoria")
    private  String editorial;

    @NotBlank(message = "El año es obligatoria")
    private  String año;

    private Long categoriaId;//relacion con la clase categoria

    @NotBlank(message = "Es obligatoria")
    private  String disponobilidad;

}

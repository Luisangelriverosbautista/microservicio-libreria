package com.jessica.prestamo.client.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibroDTO {
    private Long idLibro;
    private String titulo;
    private String editorial;
    private Integer añoPublicacion;
    private Boolean disponibilidad;
    private String categoria;
}

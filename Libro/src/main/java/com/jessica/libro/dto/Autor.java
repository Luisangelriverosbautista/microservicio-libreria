package com.jessica.libro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Autor {
    private Long id;
    private String nombre;
    private String nacionalidad;
    private LocalDate fechaNacimiento;
}

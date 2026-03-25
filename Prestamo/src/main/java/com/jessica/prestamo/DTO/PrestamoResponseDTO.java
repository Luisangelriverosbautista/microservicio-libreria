package com.jessica.prestamo.DTO;

import com.jessica.prestamo.model.EstadoPrestamo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoResponseDTO {
    private Long idPrestamo;
    private Long idUsuario;
    private String nombreUsuario;
    private Long idLibro;
    private String tituloLibro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private EstadoPrestamo estado;
}

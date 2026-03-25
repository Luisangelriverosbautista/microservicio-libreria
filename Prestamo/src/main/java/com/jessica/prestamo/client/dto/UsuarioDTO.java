package com.jessica.prestamo.client.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Long idUsuario;
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    private String direccion;
}

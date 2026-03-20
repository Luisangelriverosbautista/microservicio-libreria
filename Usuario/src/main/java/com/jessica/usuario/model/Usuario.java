package com.jessica.usuario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Es obligatorio este apartado")
    private String apellidos;

    @Email(message = "El correo debe ser valido")
    @NotBlank(message = "El correo es obligatorio")
    @Column(unique = true)
    private String correo;


    @Pattern(regexp = "^[0-9]{10}$", message = "El telefono debe ser valido")
    private String telefono;


    private  String direccion;
}

package com.jessica.prestamo.controller;
import com.jessica.prestamo.DTO.PrestamoRequestDTO;
import com.jessica.prestamo.DTO.PrestamoResponseDTO;
import com.jessica.prestamo.services.PrestamoServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
@CrossOrigin(origins = "*")
public class PrestamoController {

    @Autowired
    private PrestamoServices prestamoServices;

    @GetMapping("/traer-prestamos")
    public List<PrestamoResponseDTO> traerPrestamos() {
        return prestamoServices.listarPrestamos();
    }

    @GetMapping("/traer-prestamo/{id}")
    public ResponseEntity<PrestamoResponseDTO> traerPrestamo(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoServices.obtenerPrestamo(id));
    }

    @PostMapping("/insertar-prestamo")
    public ResponseEntity<PrestamoResponseDTO> insertarPrestamo(@Valid @RequestBody PrestamoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamoServices.crearPrestamo(request));
    }

    @PutMapping("/devolver-prestamo/{id}")
    public ResponseEntity<PrestamoResponseDTO> devolverPrestamo(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoServices.devolverPrestamo(id));
    }
}

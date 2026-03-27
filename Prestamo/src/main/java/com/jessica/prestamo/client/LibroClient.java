package com.jessica.prestamo.client;

import com.jessica.prestamo.client.dto.LibroDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "libroClient", url = "${libro.service.url}")
public interface LibroClient {

    @GetMapping("/libros/traer-libro/{id}")
    LibroDTO obtenerLibro(@PathVariable("id") Long id);

    @PutMapping("/libros/actualizar-disponibilidad/{id}")
    LibroDTO actualizarDisponibilidad(@PathVariable("id") Long id, @RequestParam("disponible") Boolean disponible);
}

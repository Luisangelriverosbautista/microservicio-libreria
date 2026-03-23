package com.jessica.libro.client;

import com.jessica.libro.dto.Autor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "autor-service", url = "http://localhost:9091")
public interface AutorClient {
    @GetMapping("/autores/traer-autor/{id}")
    Autor getAutorById(@PathVariable("id") Long id);
}
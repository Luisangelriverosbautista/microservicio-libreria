package com.jessica.libro.client;

import com.jessica.libro.dto.Autor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "Autor", url = "http://localhost:9091/autores")
public interface AutorClient {
    @GetMapping("/traer-autor/{id}")
    Autor getAutorById(@PathVariable Long id);
}
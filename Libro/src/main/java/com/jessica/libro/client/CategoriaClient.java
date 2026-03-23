package com.jessica.libro.client;



import com.jessica.libro.dto.Categoria;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name = "categoria-service", url = "http://localhost:9092")
public interface CategoriaClient {
    @GetMapping("/categorias/traer-categoria/{id}")
    Categoria getCategoriaById(@PathVariable("id") Long id);
}
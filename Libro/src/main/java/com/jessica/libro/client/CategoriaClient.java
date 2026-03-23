package com.jessica.libro.client;



import com.jessica.libro.dto.Categoria;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name = "Categoria", url = "http://localhost:9092/categorias")
public interface CategoriaClient {
    @GetMapping("/traer-categoria/{id}")
    Categoria getCategoriaById(@PathVariable Long id);
}
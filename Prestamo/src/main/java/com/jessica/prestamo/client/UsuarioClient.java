package com.jessica.prestamo.client;

import com.jessica.prestamo.client.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuarioClient", url = "${usuario.service.url}")
public interface UsuarioClient {

    @GetMapping("/usuarios/traer-usuario/{id}")
    UsuarioDTO obtenerUsuario(@PathVariable("id") Long id);
}

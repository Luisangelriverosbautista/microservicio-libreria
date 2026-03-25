package com.jessica.prestamo.services;

import com.jessica.prestamo.DTO.PrestamoRequestDTO;
import com.jessica.prestamo.DTO.PrestamoResponseDTO;
import com.jessica.prestamo.client.LibroClient;
import com.jessica.prestamo.client.UsuarioClient;
import com.jessica.prestamo.client.dto.LibroDTO;
import com.jessica.prestamo.client.dto.UsuarioDTO;
import com.jessica.prestamo.model.EstadoPrestamo;
import com.jessica.prestamo.model.Prestamo;
import com.jessica.prestamo.repository.PrestamoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoServices {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private LibroClient libroClient;

    public List<PrestamoResponseDTO> listarPrestamos() {
        return prestamoRepository.findAll().stream()
                .map(prestamo -> mapToResponse(prestamo, null, null))
                .toList();
    }

    public PrestamoResponseDTO obtenerPrestamo(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prestamo no encontrado"));
        return mapToResponse(prestamo, null, null);
    }

    @Transactional
    public PrestamoResponseDTO crearPrestamo(PrestamoRequestDTO request) {
        UsuarioDTO usuario = obtenerUsuarioValidado(request.getIdUsuario());
        LibroDTO libro = obtenerLibroValidado(request.getIdLibro());

        if (Boolean.FALSE.equals(libro.getDisponibilidad())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El libro no esta disponible");
        }

        actualizarDisponibilidadLibro(libro.getIdLibro(), false);

        Prestamo prestamo = new Prestamo();
        prestamo.setIdUsuario(usuario.getIdUsuario());
        prestamo.setIdLibro(libro.getIdLibro());
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setEstado(EstadoPrestamo.ACTIVO);

        Prestamo guardado = prestamoRepository.save(prestamo);
        String nombreUsuario = usuario.getNombre() + " " + usuario.getApellidos();
        return mapToResponse(guardado, nombreUsuario, libro.getTitulo());
    }

    @Transactional
    public PrestamoResponseDTO devolverPrestamo(Long idPrestamo) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prestamo no encontrado"));

        if (prestamo.getEstado() == EstadoPrestamo.DEVUELTO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este prestamo ya fue devuelto");
        }

        actualizarDisponibilidadLibro(prestamo.getIdLibro(), true);

        prestamo.setEstado(EstadoPrestamo.DEVUELTO);
        prestamo.setFechaDevolucion(LocalDate.now());

        Prestamo actualizado = prestamoRepository.save(prestamo);
        return mapToResponse(actualizado, null, null);
    }

    private UsuarioDTO obtenerUsuarioValidado(Long idUsuario) {
        try {
            return usuarioClient.obtenerUsuario(idUsuario);
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        } catch (FeignException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "No se pudo validar el usuario");
        }
    }

    private LibroDTO obtenerLibroValidado(Long idLibro) {
        try {
            return libroClient.obtenerLibro(idLibro);
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro no encontrado");
        } catch (FeignException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "No se pudo validar el libro");
        }
    }

    private void actualizarDisponibilidadLibro(Long idLibro, Boolean disponible) {
        try {
            libroClient.actualizarDisponibilidad(idLibro, disponible);
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro no encontrado para actualizar disponibilidad");
        } catch (FeignException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "No se pudo actualizar disponibilidad del libro");
        }
    }

    private PrestamoResponseDTO mapToResponse(Prestamo prestamo, String nombreUsuario, String tituloLibro) {
        return new PrestamoResponseDTO(
                prestamo.getIdPrestamo(),
                prestamo.getIdUsuario(),
                nombreUsuario,
                prestamo.getIdLibro(),
                tituloLibro,
                prestamo.getFechaPrestamo(),
                prestamo.getFechaDevolucion(),
                prestamo.getEstado()
        );
    }
}

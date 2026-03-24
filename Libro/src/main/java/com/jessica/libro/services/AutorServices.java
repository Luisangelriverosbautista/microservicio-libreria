package com.jessica.libro.services;
import com.jessica.libro.model.Autor;
import com.jessica.libro.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> obtenerTodos() {
        return autorRepository.findAll();
    }

    public Optional<Autor> obtenerPorId(Long id) {
        return autorRepository.findById(id);
    }

    public Autor guardarAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    public Optional<Autor> actualizarAutor(Long id, Autor autorDetalles) {
        return autorRepository.findById(id).map(autorExistente -> {
            autorExistente.setNombre(autorDetalles.getNombre());
            return autorRepository.save(autorExistente);
        });
    }

    public void eliminarAutor(Long id) {
        autorRepository.deleteById(id);
    }
}
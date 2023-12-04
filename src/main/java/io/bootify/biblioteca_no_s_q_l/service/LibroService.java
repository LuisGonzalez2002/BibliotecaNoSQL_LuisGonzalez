package io.bootify.biblioteca_no_s_q_l.service;

import io.bootify.biblioteca_no_s_q_l.domain.Libro;
import io.bootify.biblioteca_no_s_q_l.model.LibroDTO;
import io.bootify.biblioteca_no_s_q_l.repos.LibroRepository;
import io.bootify.biblioteca_no_s_q_l.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(final LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<LibroDTO> findAll() {
        final List<Libro> libroes = libroRepository.findAll(Sort.by("idBiblioteca"));
        return libroes.stream()
                .map(libro -> mapToDTO(libro, new LibroDTO()))
                .toList();
    }

    public LibroDTO get(final Long idBiblioteca) {
        return libroRepository.findById(idBiblioteca)
                .map(libro -> mapToDTO(libro, new LibroDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final LibroDTO libroDTO) {
        final Libro libro = new Libro();
        mapToEntity(libroDTO, libro);
        return libroRepository.save(libro).getIdBiblioteca();
    }

    public void update(final Long idBiblioteca, final LibroDTO libroDTO) {
        final Libro libro = libroRepository.findById(idBiblioteca)
                .orElseThrow(NotFoundException::new);
        mapToEntity(libroDTO, libro);
        libroRepository.save(libro);
    }

    public void delete(final Long idBiblioteca) {
        libroRepository.deleteById(idBiblioteca);
    }

    private LibroDTO mapToDTO(final Libro libro, final LibroDTO libroDTO) {
        libroDTO.setIdBiblioteca(libro.getIdBiblioteca());
        libroDTO.setTitulo(libro.getTitulo());
        libroDTO.setAutor(libro.getAutor());
        libroDTO.setGenero(libro.getGenero());
        libroDTO.setEstado(libro.getEstado());
        return libroDTO;
    }

    private Libro mapToEntity(final LibroDTO libroDTO, final Libro libro) {
        libro.setTitulo(libroDTO.getTitulo());
        libro.setAutor(libroDTO.getAutor());
        libro.setGenero(libroDTO.getGenero());
        libro.setEstado(libroDTO.getEstado());
        return libro;
    }

}

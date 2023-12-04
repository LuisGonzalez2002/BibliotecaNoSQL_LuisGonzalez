package io.bootify.biblioteca_no_s_q_l.service;

import io.bootify.biblioteca_no_s_q_l.domain.Bibliotecario;
import io.bootify.biblioteca_no_s_q_l.model.BibliotecarioDTO;
import io.bootify.biblioteca_no_s_q_l.repos.BibliotecarioRepository;
import io.bootify.biblioteca_no_s_q_l.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class BibliotecarioService {

    private final BibliotecarioRepository bibliotecarioRepository;

    public BibliotecarioService(final BibliotecarioRepository bibliotecarioRepository) {
        this.bibliotecarioRepository = bibliotecarioRepository;
    }

    public List<BibliotecarioDTO> findAll() {
        final List<Bibliotecario> bibliotecarios = bibliotecarioRepository.findAll(Sort.by("idBiblioteca"));
        return bibliotecarios.stream()
                .map(bibliotecario -> mapToDTO(bibliotecario, new BibliotecarioDTO()))
                .toList();
    }

    public BibliotecarioDTO get(final Long idBiblioteca) {
        return bibliotecarioRepository.findById(idBiblioteca)
                .map(bibliotecario -> mapToDTO(bibliotecario, new BibliotecarioDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final BibliotecarioDTO bibliotecarioDTO) {
        final Bibliotecario bibliotecario = new Bibliotecario();
        mapToEntity(bibliotecarioDTO, bibliotecario);
        return bibliotecarioRepository.save(bibliotecario).getIdBiblioteca();
    }

    public void update(final Long idBiblioteca, final BibliotecarioDTO bibliotecarioDTO) {
        final Bibliotecario bibliotecario = bibliotecarioRepository.findById(idBiblioteca)
                .orElseThrow(NotFoundException::new);
        mapToEntity(bibliotecarioDTO, bibliotecario);
        bibliotecarioRepository.save(bibliotecario);
    }

    public void delete(final Long idBiblioteca) {
        bibliotecarioRepository.deleteById(idBiblioteca);
    }

    private BibliotecarioDTO mapToDTO(final Bibliotecario bibliotecario,
            final BibliotecarioDTO bibliotecarioDTO) {
        bibliotecarioDTO.setIdBiblioteca(bibliotecario.getIdBiblioteca());
        bibliotecarioDTO.setNombre(bibliotecario.getNombre());
        bibliotecarioDTO.setApellido(bibliotecario.getApellido());
        bibliotecarioDTO.setZona(bibliotecario.getZona());
        return bibliotecarioDTO;
    }

    private Bibliotecario mapToEntity(final BibliotecarioDTO bibliotecarioDTO,
            final Bibliotecario bibliotecario) {
        bibliotecario.setNombre(bibliotecarioDTO.getNombre());
        bibliotecario.setApellido(bibliotecarioDTO.getApellido());
        bibliotecario.setZona(bibliotecarioDTO.getZona());
        return bibliotecario;
    }

}

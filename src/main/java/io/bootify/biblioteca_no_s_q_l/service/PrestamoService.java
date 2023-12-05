package io.bootify.biblioteca_no_s_q_l.service;

import io.bootify.biblioteca_no_s_q_l.domain.Prestamo;
import io.bootify.biblioteca_no_s_q_l.model.PrestamoDTO;
import io.bootify.biblioteca_no_s_q_l.repos.PrestamoRepository;
import io.bootify.biblioteca_no_s_q_l.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;

    public PrestamoService(final PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    public List<PrestamoDTO> findAll() {
        final List<Prestamo> prestamoes = prestamoRepository.findAll(Sort.by("idBiblioteca"));
        return prestamoes.stream()
                .map(prestamo -> mapToDTO(prestamo, new PrestamoDTO()))
                .toList();
    }

    public PrestamoDTO get(final Long idBiblioteca) {
        return prestamoRepository.findById(idBiblioteca)
                .map(prestamo -> mapToDTO(prestamo, new PrestamoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final PrestamoDTO prestamoDTO) {
        final Prestamo prestamo = new Prestamo();
        mapToEntity(prestamoDTO, prestamo);
        return prestamoRepository.save(prestamo).getIdBiblioteca();
    }

    public void update(final Long idBiblioteca, final PrestamoDTO prestamoDTO) {
        final Prestamo prestamo = prestamoRepository.findById(idBiblioteca)
                .orElseThrow(NotFoundException::new);
        mapToEntity(prestamoDTO, prestamo);
        prestamoRepository.save(prestamo);
    }

    public void delete(final Long idBiblioteca) {
        prestamoRepository.deleteById(idBiblioteca);
    }

    private PrestamoDTO mapToDTO(final Prestamo prestamo, final PrestamoDTO prestamoDTO) {
        prestamoDTO.setIdBiblioteca(prestamo.getIdBiblioteca());
        prestamoDTO.setFechaInicio(prestamo.getFechaInicio());
        prestamoDTO.setFechaDevolucion(prestamo.getFechaDevolucion());
        prestamoDTO.setEstadoLibro(prestamo.getEstadoLibro());
        prestamoDTO.setIdLibro(prestamo.getIdLibro());
        prestamoDTO.setIdLector(prestamo.getIdLector());
        return prestamoDTO;
    }

    private Prestamo mapToEntity(final PrestamoDTO prestamoDTO, final Prestamo prestamo) {
        prestamo.setFechaInicio(prestamoDTO.getFechaInicio());
        prestamo.setFechaDevolucion(prestamoDTO.getFechaDevolucion());
        prestamo.setEstadoLibro(prestamoDTO.getEstadoLibro());
        prestamo.setIdLibro(prestamoDTO.getIdLibro());
        prestamo.setIdLector(prestamoDTO.getIdLector());
        return prestamo;
    }

}

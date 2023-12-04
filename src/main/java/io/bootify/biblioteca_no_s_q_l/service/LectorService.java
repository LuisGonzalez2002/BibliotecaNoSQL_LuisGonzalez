package io.bootify.biblioteca_no_s_q_l.service;

import io.bootify.biblioteca_no_s_q_l.domain.Lector;
import io.bootify.biblioteca_no_s_q_l.model.LectorDTO;
import io.bootify.biblioteca_no_s_q_l.repos.LectorRepository;
import io.bootify.biblioteca_no_s_q_l.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class LectorService {

    private final LectorRepository lectorRepository;

    public LectorService(final LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    public List<LectorDTO> findAll() {
        final List<Lector> lectors = lectorRepository.findAll(Sort.by("idBiblioteca"));
        return lectors.stream()
                .map(lector -> mapToDTO(lector, new LectorDTO()))
                .toList();
    }

    public LectorDTO get(final Long idBiblioteca) {
        return lectorRepository.findById(idBiblioteca)
                .map(lector -> mapToDTO(lector, new LectorDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final LectorDTO lectorDTO) {
        final Lector lector = new Lector();
        mapToEntity(lectorDTO, lector);
        return lectorRepository.save(lector).getIdBiblioteca();
    }

    public void update(final Long idBiblioteca, final LectorDTO lectorDTO) {
        final Lector lector = lectorRepository.findById(idBiblioteca)
                .orElseThrow(NotFoundException::new);
        mapToEntity(lectorDTO, lector);
        lectorRepository.save(lector);
    }

    public void delete(final Long idBiblioteca) {
        lectorRepository.deleteById(idBiblioteca);
    }

    private LectorDTO mapToDTO(final Lector lector, final LectorDTO lectorDTO) {
        lectorDTO.setIdBiblioteca(lector.getIdBiblioteca());
        lectorDTO.setNombreCompleto(lector.getNombreCompleto());
        lectorDTO.setDni(lector.getDni());
        lectorDTO.setDireccion(lector.getDireccion());
        return lectorDTO;
    }

    private Lector mapToEntity(final LectorDTO lectorDTO, final Lector lector) {
        lector.setNombreCompleto(lectorDTO.getNombreCompleto());
        lector.setDni(lectorDTO.getDni());
        lector.setDireccion(lectorDTO.getDireccion());
        return lector;
    }

}

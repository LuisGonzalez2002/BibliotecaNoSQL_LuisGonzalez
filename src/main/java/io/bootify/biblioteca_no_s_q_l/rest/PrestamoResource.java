package io.bootify.biblioteca_no_s_q_l.rest;

import io.bootify.biblioteca_no_s_q_l.model.PrestamoDTO;
import io.bootify.biblioteca_no_s_q_l.service.PrestamoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/prestamos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrestamoResource {

    private final PrestamoService prestamoService;

    public PrestamoResource(final PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public ResponseEntity<List<PrestamoDTO>> getAllPrestamos() {
        return ResponseEntity.ok(prestamoService.findAll());
    }

    @GetMapping("/{idBiblioteca}")
    public ResponseEntity<PrestamoDTO> getPrestamo(
            @PathVariable(name = "idBiblioteca") final Long idBiblioteca) {
        return ResponseEntity.ok(prestamoService.get(idBiblioteca));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createPrestamo(@RequestBody @Valid final PrestamoDTO prestamoDTO) {
        final Long createdIdBiblioteca = prestamoService.create(prestamoDTO);
        return new ResponseEntity<>(createdIdBiblioteca, HttpStatus.CREATED);
    }

    @PutMapping("/{idBiblioteca}")
    public ResponseEntity<Long> updatePrestamo(
            @PathVariable(name = "idBiblioteca") final Long idBiblioteca,
            @RequestBody @Valid final PrestamoDTO prestamoDTO) {
        prestamoService.update(idBiblioteca, prestamoDTO);
        return ResponseEntity.ok(idBiblioteca);
    }

    @DeleteMapping("/{idBiblioteca}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deletePrestamo(
            @PathVariable(name = "idBiblioteca") final Long idBiblioteca) {
        prestamoService.delete(idBiblioteca);
        return ResponseEntity.noContent().build();
    }

}

package io.bootify.biblioteca_no_s_q_l.rest;

import io.bootify.biblioteca_no_s_q_l.model.BibliotecarioDTO;
import io.bootify.biblioteca_no_s_q_l.service.BibliotecarioService;
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
@RequestMapping(value = "/api/bibliotecarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class BibliotecarioResource {

    private final BibliotecarioService bibliotecarioService;

    public BibliotecarioResource(final BibliotecarioService bibliotecarioService) {
        this.bibliotecarioService = bibliotecarioService;
    }

    @GetMapping
    public ResponseEntity<List<BibliotecarioDTO>> getAllBibliotecarios() {
        return ResponseEntity.ok(bibliotecarioService.findAll());
    }

    @GetMapping("/{idBiblioteca}")
    public ResponseEntity<BibliotecarioDTO> getBibliotecario(
            @PathVariable(name = "idBiblioteca") final Long idBiblioteca) {
        return ResponseEntity.ok(bibliotecarioService.get(idBiblioteca));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createBibliotecario(
            @RequestBody @Valid final BibliotecarioDTO bibliotecarioDTO) {
        final Long createdIdBiblioteca = bibliotecarioService.create(bibliotecarioDTO);
        return new ResponseEntity<>(createdIdBiblioteca, HttpStatus.CREATED);
    }

    @PutMapping("/{idBiblioteca}")
    public ResponseEntity<Long> updateBibliotecario(
            @PathVariable(name = "idBiblioteca") final Long idBiblioteca,
            @RequestBody @Valid final BibliotecarioDTO bibliotecarioDTO) {
        bibliotecarioService.update(idBiblioteca, bibliotecarioDTO);
        return ResponseEntity.ok(idBiblioteca);
    }

    @DeleteMapping("/{idBiblioteca}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteBibliotecario(
            @PathVariable(name = "idBiblioteca") final Long idBiblioteca) {
        bibliotecarioService.delete(idBiblioteca);
        return ResponseEntity.noContent().build();
    }

}

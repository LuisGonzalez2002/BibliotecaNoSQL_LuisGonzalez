package io.bootify.biblioteca_no_s_q_l.rest;

import io.bootify.biblioteca_no_s_q_l.model.LectorDTO;
import io.bootify.biblioteca_no_s_q_l.service.LectorService;
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
@RequestMapping(value = "/api/lectors", produces = MediaType.APPLICATION_JSON_VALUE)
public class LectorResource {

    private final LectorService lectorService;

    public LectorResource(final LectorService lectorService) {
        this.lectorService = lectorService;
    }

    @GetMapping
    public ResponseEntity<List<LectorDTO>> getAllLectors() {
        return ResponseEntity.ok(lectorService.findAll());
    }

    @GetMapping("/{idBiblioteca}")
    public ResponseEntity<LectorDTO> getLector(
            @PathVariable(name = "idBiblioteca") final Long idBiblioteca) {
        return ResponseEntity.ok(lectorService.get(idBiblioteca));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createLector(@RequestBody @Valid final LectorDTO lectorDTO) {
        final Long createdIdBiblioteca = lectorService.create(lectorDTO);
        return new ResponseEntity<>(createdIdBiblioteca, HttpStatus.CREATED);
    }

    @PutMapping("/{idBiblioteca}")
    public ResponseEntity<Long> updateLector(
            @PathVariable(name = "idBiblioteca") final Long idBiblioteca,
            @RequestBody @Valid final LectorDTO lectorDTO) {
        lectorService.update(idBiblioteca, lectorDTO);
        return ResponseEntity.ok(idBiblioteca);
    }

    @DeleteMapping("/{idBiblioteca}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteLector(
            @PathVariable(name = "idBiblioteca") final Long idBiblioteca) {
        lectorService.delete(idBiblioteca);
        return ResponseEntity.noContent().build();
    }

}

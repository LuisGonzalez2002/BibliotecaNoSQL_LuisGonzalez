package io.bootify.biblioteca_no_s_q_l.rest;

import io.bootify.biblioteca_no_s_q_l.model.LibroDTO;
import io.bootify.biblioteca_no_s_q_l.service.LibroService;
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
@RequestMapping(value = "/api/libros", produces = MediaType.APPLICATION_JSON_VALUE)
public class LibroResource {

    private final LibroService libroService;

    public LibroResource(final LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<LibroDTO>> getAllLibros() {
        return ResponseEntity.ok(libroService.findAll());
    }

    @GetMapping("/{idBiblioteca}")
    public ResponseEntity<LibroDTO> getLibro(
            @PathVariable(name = "idBiblioteca") final Long idBiblioteca) {
        return ResponseEntity.ok(libroService.get(idBiblioteca));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createLibro(@RequestBody @Valid final LibroDTO libroDTO) {
        final Long createdIdBiblioteca = libroService.create(libroDTO);
        return new ResponseEntity<>(createdIdBiblioteca, HttpStatus.CREATED);
    }

    @PutMapping("/{idBiblioteca}")
    public ResponseEntity<Long> updateLibro(
            @PathVariable(name = "idBiblioteca") final Long idBiblioteca,
            @RequestBody @Valid final LibroDTO libroDTO) {
        libroService.update(idBiblioteca, libroDTO);
        return ResponseEntity.ok(idBiblioteca);
    }

    @DeleteMapping("/{idBiblioteca}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteLibro(
            @PathVariable(name = "idBiblioteca") final Long idBiblioteca) {
        libroService.delete(idBiblioteca);
        return ResponseEntity.noContent().build();
    }

}

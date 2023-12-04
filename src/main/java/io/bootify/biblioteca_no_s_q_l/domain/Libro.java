package io.bootify.biblioteca_no_s_q_l.domain;

import io.bootify.biblioteca_no_s_q_l.model.EstadoLibro;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("libros")
@Getter
@Setter
public class Libro extends Biblioteca {

    @NotNull
    @Size(max = 255)
    private String titulo;

    @NotNull
    @Size(max = 255)
    private String autor;

    @NotNull
    @Size(max = 255)
    private String genero;

    @NotNull
    private EstadoLibro estado;

}

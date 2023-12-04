package io.bootify.biblioteca_no_s_q_l.domain;

import io.bootify.biblioteca_no_s_q_l.model.EstadoLibro;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("prestamos")
@Getter
@Setter
public class Prestamo extends Biblioteca {

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private LocalDate fechaDevolucion;

    private EstadoLibro estadoLibro;

}

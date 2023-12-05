package io.bootify.biblioteca_no_s_q_l.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PrestamoDTO {

    private Long idBiblioteca;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private LocalDate fechaDevolucion;

    private EstadoLibro estadoLibro;

    @NotNull
    private Integer idLibro;

    @NotNull
    private Integer idLector;

}

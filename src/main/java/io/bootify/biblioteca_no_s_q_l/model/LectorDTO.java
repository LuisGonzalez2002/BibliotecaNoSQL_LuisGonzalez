package io.bootify.biblioteca_no_s_q_l.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LectorDTO {

    private Long idBiblioteca;

    @NotNull
    @Size(max = 255)
    private String nombreCompleto;

    @NotNull
    @Size(max = 255)
    private String dni;

    @NotNull
    @Size(max = 255)
    private String direccion;

}

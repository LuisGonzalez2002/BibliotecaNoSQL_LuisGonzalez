package io.bootify.biblioteca_no_s_q_l.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BibliotecarioDTO {

    private Long idBiblioteca;

    @NotNull
    @Size(max = 255)
    private String nombre;

    @Size(max = 255)
    private String apellido;

    @Size(max = 255)
    private String zona;

}

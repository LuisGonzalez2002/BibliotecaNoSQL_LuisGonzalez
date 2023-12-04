package io.bootify.biblioteca_no_s_q_l.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("lectors")
@Getter
@Setter
public class Lector extends Biblioteca {

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

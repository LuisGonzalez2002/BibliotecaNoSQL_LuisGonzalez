package io.bootify.biblioteca_no_s_q_l.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("bibliotecarios")
@Getter
@Setter
public class Bibliotecario extends Biblioteca {

    @NotNull
    @Size(max = 255)
    private String nombre;

    @Size(max = 255)
    private String apellido;

    @Size(max = 255)
    private String zona;

}

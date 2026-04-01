package com.susbsonic.usuarios.models.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArtistaRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El día de actuación es obligatorio")
    private String dia;

    private String img;
    private String spoty;
}

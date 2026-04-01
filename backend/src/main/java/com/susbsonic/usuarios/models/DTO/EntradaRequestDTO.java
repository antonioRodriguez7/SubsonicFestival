package com.susbsonic.usuarios.models.DTO;

import com.susbsonic.usuarios.models.Entrada.EstadoEntrada;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EntradaRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
    private BigDecimal precio;

    private String descripcion;
    private String etiqueta;
    private String tipoEtiqueta;
    private EstadoEntrada estado;
    private String img;
}

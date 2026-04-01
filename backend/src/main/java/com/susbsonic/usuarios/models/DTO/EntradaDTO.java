package com.susbsonic.usuarios.models.DTO;

import com.susbsonic.usuarios.models.Entrada.EstadoEntrada;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EntradaDTO {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private String descripcion;
    private String etiqueta;
    private String tipoEtiqueta;
    private EstadoEntrada estado;
    private String img;
}

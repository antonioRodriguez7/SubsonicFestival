package com.susbsonic.usuarios.models.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class EspacioRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String zonaGeneral;
    private String caracteristica;
    private String tipo;
    private String evento;
    private String lugar;
    private String tamano;
    private BigDecimal precio;
    private String ubicacion;
    private String descripcion;
    private String capacidad;
    private List<String> servicios;
    private String disponibilidad;
    private String imagen;
    private String negocioNombre;
    private String negocioCategoria;
}

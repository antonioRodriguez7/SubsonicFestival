package com.susbsonic.usuarios.models.DTO;

import lombok.Data;

@Data
public class ServicioDTO {
    private Long id;
    private Long espacioId;
    private String espacioNombre;
    /** ID del proveedor en el microservicio de usuarios */
    private Long proveedorId;
    private String nombre;
    private String tipo;
    private String descripcion;
    /** Fechas como texto libre: "17-20 julio", "Solo sábado", etc. */
    private String fechas;
}

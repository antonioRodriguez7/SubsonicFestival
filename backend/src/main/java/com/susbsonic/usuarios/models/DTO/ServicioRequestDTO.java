package com.susbsonic.usuarios.models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServicioRequestDTO {

    @NotNull(message = "El ID de espacio es obligatorio")
    private Long espacioId;

    /**
     * ID del proveedor que crea el servicio.
     * Viene del token JWT del microservicio de usuarios.
     */
    @NotNull(message = "El ID de proveedor es obligatorio")
    private Long proveedorId;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String tipo;
    private String descripcion;
    /** Fechas como texto libre: "17-20 julio", "Solo sábado", etc. */
    private String fechas;
}

package com.susbsonic.usuarios.models;

import jakarta.persistence.*;
import lombok.*;



/**
 * Servicio concreto que un proveedor ofrece dentro de un espacio contratado.
 * El proveedor se identifica por su ID externo (viene del micro de usuarios).
 */
@Entity
@Table(name = "servicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Espacio al que pertenece este servicio */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "espacio_id", nullable = false)
    private Espacio espacio;

    /**
     * ID del proveedor en el microservicio de usuarios externo.
     * No es FK: la integridad la gestiona el backend de usuarios.
     */
    @Column(name = "proveedor_id")
    private Long proveedorId;

    @Column(nullable = false, length = 150)
    private String nombre;

    /** Tipo de servicio: Bebidas, Comida, Merchandising, Entretenimiento... */
    @Column(length = 50)
    private String tipo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    /**
     * Fechas de operación como texto libre: "17-20 julio", "Solo sábado", etc.
     */
    @Column(length = 100)
    private String fechas;
}

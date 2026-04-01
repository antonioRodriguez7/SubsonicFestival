package com.susbsonic.usuarios.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Espacio físico disponible o contratado en el festival.
 * Los campos de capacidad y disponibilidad son texto libre para reflejar
 * exactamente los valores del frontend ("20 stands", "Disponible", "Reservado").
 */
@Entity
@Table(name = "espacios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Espacio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    /** Zona general del recinto: Norte, Centro, Sur, Este, Oeste */
    @Column(name = "zona_general", length = 50)
    private String zonaGeneral;

    /** Característica principal del espacio (e.g. "Terreno llano cubierto con carpa") */
    @Column(length = 255)
    private String caracteristica;

    /** Tipo de espacio: food_truck, merchandising, barra, etc. */
    @Column(length = 50)
    private String tipo;

    /** Nombre del evento asociado */
    @Column(length = 100)
    private String evento;

    /** Ubicación/zona dentro del recinto festivalero */
    @Column(length = 150)
    private String lugar;

    /** Dimensiones del espacio (e.g. "500m²") */
    @Column(name = "tamano", length = 50)
    private String tamano;

    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    /** Descripción de la ubicación exacta */
    @Column(length = 255)
    private String ubicacion;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    /** Capacidad descriptiva: "20 stands", "5 barras", "1 escenario completo"... */
    @Column(length = 100)
    private String capacidad;

    /**
     * Lista de servicios incluidos en el espacio.
     * E.g.: ["Electricidad", "Agua corriente", "Wi-Fi"]
     */
    @ElementCollection
    @CollectionTable(name = "espacio_servicios_incluidos", joinColumns = @JoinColumn(name = "espacio_id"))
    @Column(name = "servicio")
    private List<String> servicios;

    /**
     * Estado del espacio: "Disponible", "Reservado".
     * Texto libre para coincidir con los valores del frontend.
     */
    @Builder.Default
    @Column(length = 30)
    private String disponibilidad = "Disponible";

    /** Ruta relativa o URL de la imagen del espacio */
    @Column(length = 255)
    private String imagen;

    // ── Campos negocio (solo si el espacio está reservado/contratado) ──────────

    /** Nombre del negocio que ha contratado este espacio (nullable) */
    @Column(name = "negocio_nombre", length = 200)
    private String negocioNombre;

    /** Categoría del negocio (nullable): "Comida", "Entretenimiento", etc. */
    @Column(name = "negocio_categoria", length = 100)
    private String negocioCategoria;
}

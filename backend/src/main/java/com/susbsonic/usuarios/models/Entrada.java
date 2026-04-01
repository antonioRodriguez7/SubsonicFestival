package com.susbsonic.usuarios.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Entidad que representa un tipo de entrada del festival.
 * Ejemplos: Abono General, Abono VIP, Dream VIP.
 */
@Entity
@Table(name = "entradas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    /** Texto de la etiqueta visible (e.g. "MÁS VENDIDO") */
    @Column(length = 50)
    private String etiqueta;

    /** Tipo visual de etiqueta: popular, limitado, nuevo */
    @Column(name = "tipo_etiqueta", length = 20)
    private String tipoEtiqueta;

    /** Estado: disponible / agotado */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private EstadoEntrada estado = EstadoEntrada.disponible;

    /** Ruta relativa o URL de la imagen */
    @Column(length = 255)
    private String img;

    public enum EstadoEntrada {
        disponible, agotado
    }
}

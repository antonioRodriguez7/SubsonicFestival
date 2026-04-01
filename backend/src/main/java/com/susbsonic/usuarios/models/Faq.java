package com.susbsonic.usuarios.models;

import jakarta.persistence.*;
import lombok.*;

/**
 * Pregunta frecuente (FAQ).
 * Se separa por targetRole: USUARIO o PROVEEDOR.
 */
@Entity
@Table(name = "faqs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Pregunta */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String pregunta;

    /** Respuesta */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String respuesta;

    /**
     * A quién va dirigida la FAQ:
     * USUARIO = visible en la sección de usuarios.
     * PROVEEDOR = visible en la sección de proveedores.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "target_role", nullable = false)
    @Builder.Default
    private TargetRole targetRole = TargetRole.USUARIO;

    /** Orden de aparición dentro de su grupo */
    @Builder.Default
    private Integer orden = 0;

    public enum TargetRole {
        USUARIO, PROVEEDOR
    }
}

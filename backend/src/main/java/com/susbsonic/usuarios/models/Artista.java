package com.susbsonic.usuarios.models;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa un artista que actúa en el festival.
 */
@Entity
@Table(name = "artistas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    /**
     * Día de actuación.
     * Ejemplos: "Viernes 17 Julio", "Sábado 18 Julio", "Domingo 19 Julio"
     */
    @Column(nullable = false, length = 50)
    private String dia;

    /** Ruta relativa o URL de la imagen del artista */
    @Column(length = 255)
    private String img;

    /** Enlace al perfil de Spotify del artista */
    @Column(length = 500)
    private String spoty;
}

package com.susbsonic.usuarios.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que el servidor devuelve al frontend cuando un usuario hace Login o se Registra con éxito.
 * Contiene el token JWT, el rol y los datos básicos del usuario para mostrar en el perfil.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {

    private String token;
    private String role;
    private String message;

    // Datos del usuario para el perfil
    private Long   id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String bio;

}
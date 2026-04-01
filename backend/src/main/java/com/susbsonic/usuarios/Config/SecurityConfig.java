package com.susbsonic.usuarios.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. CONFIGURACIÓN DE CORS MEJORADA
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("http://localhost:5173"));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("*")); // Permitimos todos los headers para evitar el 403
                    config.setAllowCredentials(true);
                    return config;
                }))

                // 2. DESACTIVAR CSRF
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth
                        // 1. Todo lo relacionado con AUTH es público
                        .requestMatchers("/api/auth/**").permitAll()

                        // 2. CONTENIDO PÚBLICO (Lectura para el Home/Cartel)
                        // He añadido /api/spaces/** porque también lo tienes en el proyecto
                        .requestMatchers(HttpMethod.GET, "/api/artistas/**", "/api/entradas/**", "/api/espacios/**").permitAll()

                        // 3. GESTIÓN (Solo Admin)
                        // Esto protege la creación/borrado de artistas, tickets y espacios
                        .requestMatchers(HttpMethod.POST, "/api/artistas/**", "/api/entradas/**", "/api/espacios/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/artistas/**", "/api/entradas/**", "/api/espacios/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/artistas/**", "/api/entradas/**", "/api/espacios/**").hasAnyAuthority("ROLE_ADMIN")

                        // 4. COMPRAS Y PERFIL: Requiere Token
                        .requestMatchers("/api/purchases/**", "/api/users/**").authenticated()

                        // 5. CUALQUIER OTRA RUTA
                        .anyRequest().permitAll()
                )

                // 4. GESTIÓN DE SESIÓN STATELESS (JWT)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 5. FILTROS Y PROVEEDOR
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
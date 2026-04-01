package com.susbsonic.usuarios.Repositories;

import com.susbsonic.usuarios.models.Espacio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EspacioRepository extends JpaRepository<Espacio, Long> {
    /** Busca por disponibilidad: "Disponible" o "Reservado" */
    List<Espacio> findByDisponibilidad(String disponibilidad);
    List<Espacio> findByTipo(String tipo);
    List<Espacio> findByZonaGeneral(String zonaGeneral);
}

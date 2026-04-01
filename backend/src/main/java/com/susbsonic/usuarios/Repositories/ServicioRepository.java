package com.susbsonic.usuarios.Repositories;

import com.susbsonic.usuarios.models.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    List<Servicio> findByProveedorId(Long proveedorId);
    List<Servicio> findByEspacioId(Long espacioId);
}

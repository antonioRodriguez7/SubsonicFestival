package com.susbsonic.usuarios.Repositories;

import com.susbsonic.usuarios.models.Entrada;
import com.susbsonic.usuarios.models.Entrada.EstadoEntrada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntradaRepository extends JpaRepository<Entrada, Long> {
    List<Entrada> findByEstado(EstadoEntrada estado);
}

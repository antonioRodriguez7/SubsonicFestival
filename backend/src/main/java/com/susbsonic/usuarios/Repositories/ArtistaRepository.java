package com.susbsonic.usuarios.Repositories;

import com.susbsonic.usuarios.models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    List<Artista> findByDia(String dia);
    List<Artista> findByNombreContainingIgnoreCase(String nombre);
}

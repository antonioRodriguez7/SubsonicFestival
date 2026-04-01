package com.susbsonic.usuarios.Services;

import com.susbsonic.usuarios.models.DTO.ArtistaDTO;
import com.susbsonic.usuarios.models.DTO.ArtistaRequestDTO;
import com.susbsonic.usuarios.models.Artista;
import com.susbsonic.usuarios.Repositories.ArtistaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArtistaService {

    private final ArtistaRepository artistaRepository;

    public List<ArtistaDTO> getAllArtistas() {
        return artistaRepository.findAll().stream().map(this::toDTO).toList();
    }

    public List<ArtistaDTO> getArtistasByDia(String dia) {
        return artistaRepository.findByDia(dia).stream().map(this::toDTO).toList();
    }

    public ArtistaDTO getArtistaById(Long id) {
        return artistaRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Artista no encontrado con id: " + id));
    }

    @Transactional
    public ArtistaDTO crearArtista(ArtistaRequestDTO dto) {
        Artista artista = Artista.builder()
                .nombre(dto.getNombre())
                .dia(dto.getDia())
                .img(dto.getImg())
                .spoty(dto.getSpoty())
                .build();
        return toDTO(artistaRepository.save(artista));
    }

    @Transactional
    public ArtistaDTO actualizarArtista(Long id, ArtistaRequestDTO dto) {
        Artista artista = artistaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Artista no encontrado con id: " + id));

        artista.setNombre(dto.getNombre());
        artista.setDia(dto.getDia());
        artista.setImg(dto.getImg());
        artista.setSpoty(dto.getSpoty());

        return toDTO(artistaRepository.save(artista));
    }

    @Transactional
    public void eliminarArtista(Long id) {
        if (!artistaRepository.existsById(id)) {
            throw new EntityNotFoundException("Artista no encontrado con id: " + id);
        }
        artistaRepository.deleteById(id);
    }

    // ── Mapeo ──────────────────────────────────────────────────────────────────

    private ArtistaDTO toDTO(Artista a) {
        ArtistaDTO dto = new ArtistaDTO();
        dto.setId(a.getId());
        dto.setNombre(a.getNombre());
        dto.setDia(a.getDia());
        dto.setImg(a.getImg());
        dto.setSpoty(a.getSpoty());
        return dto;
    }
}

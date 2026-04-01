package com.susbsonic.usuarios.Services;

import com.susbsonic.usuarios.models.DTO.EntradaDTO;
import com.susbsonic.usuarios.models.DTO.EntradaRequestDTO;
import com.susbsonic.usuarios.models.Entrada;
import com.susbsonic.usuarios.models.Entrada.EstadoEntrada;
import com.susbsonic.usuarios.Repositories.EntradaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EntradaService {

    private final EntradaRepository entradaRepository;

    public List<EntradaDTO> getAllEntradas() {
        return entradaRepository.findAll().stream().map(this::toDTO).toList();
    }

    public List<EntradaDTO> getEntradasDisponibles() {
        return entradaRepository.findByEstado(EstadoEntrada.disponible).stream().map(this::toDTO).toList();
    }

    public EntradaDTO getEntradaById(Long id) {
        return entradaRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Entrada no encontrada con id: " + id));
    }

    @Transactional
    public EntradaDTO crearEntrada(EntradaRequestDTO dto) {
        Entrada entrada = Entrada.builder()
                .nombre(dto.getNombre())
                .precio(dto.getPrecio())
                .descripcion(dto.getDescripcion())
                .etiqueta(dto.getEtiqueta())
                .tipoEtiqueta(dto.getTipoEtiqueta())
                .estado(dto.getEstado() != null ? dto.getEstado() : EstadoEntrada.disponible)
                .img(dto.getImg())
                .build();
        return toDTO(entradaRepository.save(entrada));
    }

    @Transactional
    public EntradaDTO actualizarEntrada(Long id, EntradaRequestDTO dto) {
        Entrada entrada = entradaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entrada no encontrada con id: " + id));

        entrada.setNombre(dto.getNombre());
        entrada.setPrecio(dto.getPrecio());
        entrada.setDescripcion(dto.getDescripcion());
        entrada.setEtiqueta(dto.getEtiqueta());
        entrada.setTipoEtiqueta(dto.getTipoEtiqueta());
        if (dto.getEstado() != null) entrada.setEstado(dto.getEstado());
        entrada.setImg(dto.getImg());

        return toDTO(entradaRepository.save(entrada));
    }

    @Transactional
    public void eliminarEntrada(Long id) {
        if (!entradaRepository.existsById(id)) {
            throw new EntityNotFoundException("Entrada no encontrada con id: " + id);
        }
        entradaRepository.deleteById(id);
    }

    // ── Mapeo ──────────────────────────────────────────────────────────────────

    private EntradaDTO toDTO(Entrada e) {
        EntradaDTO dto = new EntradaDTO();
        dto.setId(e.getId());
        dto.setNombre(e.getNombre());
        dto.setPrecio(e.getPrecio());
        dto.setDescripcion(e.getDescripcion());
        dto.setEtiqueta(e.getEtiqueta());
        dto.setTipoEtiqueta(e.getTipoEtiqueta());
        dto.setEstado(e.getEstado());
        dto.setImg(e.getImg());
        return dto;
    }
}

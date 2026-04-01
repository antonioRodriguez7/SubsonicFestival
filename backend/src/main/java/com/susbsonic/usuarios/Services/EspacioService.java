package com.susbsonic.usuarios.Services;

import com.susbsonic.usuarios.models.DTO.EspacioDTO;
import com.susbsonic.usuarios.models.DTO.EspacioRequestDTO;
import com.susbsonic.usuarios.models.Espacio;
import com.susbsonic.usuarios.Repositories.EspacioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EspacioService {

    private final EspacioRepository espacioRepository;

    public List<EspacioDTO> getAllEspacios() {
        return espacioRepository.findAll().stream().map(this::toDTO).toList();
    }

    public List<EspacioDTO> getEspaciosDisponibles() {
        return espacioRepository.findByDisponibilidad("Disponible").stream().map(this::toDTO).toList();
    }

    public List<EspacioDTO> getEspaciosReservados() {
        return espacioRepository.findByDisponibilidad("Reservado").stream().map(this::toDTO).toList();
    }

    public EspacioDTO getEspacioById(Long id) {
        return espacioRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Espacio no encontrado con id: " + id));
    }

    @Transactional
    public EspacioDTO crearEspacio(EspacioRequestDTO dto) {
        return toDTO(espacioRepository.save(fromDTO(new Espacio(), dto)));
    }

    @Transactional
    public EspacioDTO actualizarEspacio(Long id, EspacioRequestDTO dto) {
        Espacio espacio = espacioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Espacio no encontrado con id: " + id));
        return toDTO(espacioRepository.save(fromDTO(espacio, dto)));
    }

    @Transactional
    public void eliminarEspacio(Long id) {
        if (!espacioRepository.existsById(id)) {
            throw new EntityNotFoundException("Espacio no encontrado con id: " + id);
        }
        espacioRepository.deleteById(id);
    }

    // ── Mapeo ──────────────────────────────────────────────────────────────────

    private EspacioDTO toDTO(Espacio e) {
        EspacioDTO dto = new EspacioDTO();
        dto.setId(e.getId());
        dto.setNombre(e.getNombre());
        dto.setZonaGeneral(e.getZonaGeneral());
        dto.setCaracteristica(e.getCaracteristica());
        dto.setTipo(e.getTipo());
        dto.setEvento(e.getEvento());
        dto.setLugar(e.getLugar());
        dto.setTamano(e.getTamano());
        dto.setPrecio(e.getPrecio());
        dto.setUbicacion(e.getUbicacion());
        dto.setDescripcion(e.getDescripcion());
        dto.setCapacidad(e.getCapacidad());
        dto.setServicios(e.getServicios());
        dto.setDisponibilidad(e.getDisponibilidad());
        dto.setImagen(e.getImagen());
        dto.setNegocioNombre(e.getNegocioNombre());
        dto.setNegocioCategoria(e.getNegocioCategoria());
        return dto;
    }

    private Espacio fromDTO(Espacio espacio, EspacioRequestDTO dto) {
        espacio.setNombre(dto.getNombre());
        espacio.setZonaGeneral(dto.getZonaGeneral());
        espacio.setCaracteristica(dto.getCaracteristica());
        espacio.setTipo(dto.getTipo());
        espacio.setEvento(dto.getEvento());
        espacio.setLugar(dto.getLugar());
        espacio.setTamano(dto.getTamano());
        espacio.setPrecio(dto.getPrecio());
        espacio.setUbicacion(dto.getUbicacion());
        espacio.setDescripcion(dto.getDescripcion());
        espacio.setCapacidad(dto.getCapacidad());
        espacio.setServicios(dto.getServicios());
        if (dto.getDisponibilidad() != null) espacio.setDisponibilidad(dto.getDisponibilidad());
        espacio.setImagen(dto.getImagen());
        espacio.setNegocioNombre(dto.getNegocioNombre());
        espacio.setNegocioCategoria(dto.getNegocioCategoria());
        return espacio;
    }
}

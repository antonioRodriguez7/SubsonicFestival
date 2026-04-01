package com.susbsonic.usuarios.Services;

import com.susbsonic.usuarios.models.DTO.ServicioDTO;
import com.susbsonic.usuarios.models.DTO.ServicioRequestDTO;
import com.susbsonic.usuarios.models.Espacio;
import com.susbsonic.usuarios.models.Servicio;
import com.susbsonic.usuarios.Repositories.EspacioRepository;
import com.susbsonic.usuarios.Repositories.ServicioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServicioService {

    private final ServicioRepository servicioRepository;
    private final EspacioRepository espacioRepository;

    public List<ServicioDTO> getAllServicios() {
        return servicioRepository.findAll().stream().map(this::toDTO).toList();
    }

    public List<ServicioDTO> getServiciosByProveedor(Long proveedorId) {
        return servicioRepository.findByProveedorId(proveedorId).stream().map(this::toDTO).toList();
    }

    public List<ServicioDTO> getServiciosByEspacio(Long espacioId) {
        return servicioRepository.findByEspacioId(espacioId).stream().map(this::toDTO).toList();
    }

    public ServicioDTO getServicioById(Long id) {
        return servicioRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado con id: " + id));
    }

    @Transactional
    public ServicioDTO crearServicio(ServicioRequestDTO dto) {
        Espacio espacio = espacioRepository.findById(dto.getEspacioId())
                .orElseThrow(() -> new EntityNotFoundException("Espacio no encontrado con id: " + dto.getEspacioId()));

        Servicio servicio = Servicio.builder()
                .espacio(espacio)
                .proveedorId(dto.getProveedorId())
                .nombre(dto.getNombre())
                .tipo(dto.getTipo())
                .descripcion(dto.getDescripcion())
                .fechas(dto.getFechas())
                .build();

        return toDTO(servicioRepository.save(servicio));
    }

    @Transactional
    public ServicioDTO actualizarServicio(Long id, ServicioRequestDTO dto) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado con id: " + id));

        Espacio espacio = espacioRepository.findById(dto.getEspacioId())
                .orElseThrow(() -> new EntityNotFoundException("Espacio no encontrado con id: " + dto.getEspacioId()));

        servicio.setEspacio(espacio);
        servicio.setProveedorId(dto.getProveedorId());
        servicio.setNombre(dto.getNombre());
        servicio.setTipo(dto.getTipo());
        servicio.setDescripcion(dto.getDescripcion());
        servicio.setFechas(dto.getFechas());

        return toDTO(servicioRepository.save(servicio));
    }

    @Transactional
    public void eliminarServicio(Long id) {
        if (!servicioRepository.existsById(id)) {
            throw new EntityNotFoundException("Servicio no encontrado con id: " + id);
        }
        servicioRepository.deleteById(id);
    }

    // ── Mapeo ──────────────────────────────────────────────────────────────────

    private ServicioDTO toDTO(Servicio s) {
        ServicioDTO dto = new ServicioDTO();
        dto.setId(s.getId());
        dto.setEspacioId(s.getEspacio() != null ? s.getEspacio().getId() : null);
        dto.setEspacioNombre(s.getEspacio() != null ? s.getEspacio().getNombre() : null);
        dto.setProveedorId(s.getProveedorId());
        dto.setNombre(s.getNombre());
        dto.setTipo(s.getTipo());
        dto.setDescripcion(s.getDescripcion());
        dto.setFechas(s.getFechas());
        return dto;
    }
}

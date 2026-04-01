package com.susbsonic.usuarios.Services;

import com.susbsonic.usuarios.models.DTO.FaqDTO;
import com.susbsonic.usuarios.models.Faq;
import com.susbsonic.usuarios.models.Faq.TargetRole;
import com.susbsonic.usuarios.Repositories.FaqRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FaqService {

    private final FaqRepository faqRepository;

    public List<FaqDTO> getFaqsUsuarios() {
        return faqRepository.findByTargetRoleOrderByOrdenAsc(TargetRole.USUARIO)
                .stream().map(this::toDTO).toList();
    }

    public List<FaqDTO> getFaqsProveedores() {
        return faqRepository.findByTargetRoleOrderByOrdenAsc(TargetRole.PROVEEDOR)
                .stream().map(this::toDTO).toList();
    }

    public List<FaqDTO> getAllFaqs() {
        return faqRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Transactional
    public FaqDTO crearFaq(FaqDTO dto) {
        Faq faq = Faq.builder()
                .pregunta(dto.getPregunta())
                .respuesta(dto.getRespuesta())
                .targetRole(dto.getTargetRole() != null ? dto.getTargetRole() : TargetRole.USUARIO)
                .orden(dto.getOrden() != null ? dto.getOrden() : 0)
                .build();
        return toDTO(faqRepository.save(faq));
    }

    @Transactional
    public FaqDTO actualizarFaq(Long id, FaqDTO dto) {
        Faq faq = faqRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FAQ no encontrada con id: " + id));
        faq.setPregunta(dto.getPregunta());
        faq.setRespuesta(dto.getRespuesta());
        if (dto.getTargetRole() != null) faq.setTargetRole(dto.getTargetRole());
        if (dto.getOrden() != null) faq.setOrden(dto.getOrden());
        return toDTO(faqRepository.save(faq));
    }

    @Transactional
    public void eliminarFaq(Long id) {
        if (!faqRepository.existsById(id)) {
            throw new EntityNotFoundException("FAQ no encontrada con id: " + id);
        }
        faqRepository.deleteById(id);
    }

    // ── Mapeo ──────────────────────────────────────────────────────────────────

    private FaqDTO toDTO(Faq f) {
        FaqDTO dto = new FaqDTO();
        dto.setId(f.getId());
        dto.setPregunta(f.getPregunta());
        dto.setRespuesta(f.getRespuesta());
        dto.setTargetRole(f.getTargetRole());
        dto.setOrden(f.getOrden());
        return dto;
    }
}

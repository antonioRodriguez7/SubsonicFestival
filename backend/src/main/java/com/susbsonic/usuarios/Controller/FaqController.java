package com.susbsonic.usuarios.Controller;

import com.susbsonic.usuarios.models.DTO.FaqDTO;
import com.susbsonic.usuarios.Services.FaqService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faqs")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

    /** GET /api/faqs/usuarios → FAQs para usuarios (público) */
    @GetMapping("/usuarios")
    public ResponseEntity<List<FaqDTO>> getFaqsUsuarios() {
        return ResponseEntity.ok(faqService.getFaqsUsuarios());
    }

    /** GET /api/faqs/proveedores → FAQs para proveedores (público) */
    @GetMapping("/proveedores")
    public ResponseEntity<List<FaqDTO>> getFaqsProveedores() {
        return ResponseEntity.ok(faqService.getFaqsProveedores());
    }

    /** GET /api/faqs → Todas las FAQs */
    @GetMapping
    public ResponseEntity<List<FaqDTO>> getAll() {
        return ResponseEntity.ok(faqService.getAllFaqs());
    }

    /** POST /api/faqs */
    @PostMapping
    public ResponseEntity<FaqDTO> crear(@Valid @RequestBody FaqDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(faqService.crearFaq(dto));
    }

    /** PUT /api/faqs/{id} */
    @PutMapping("/{id}")
    public ResponseEntity<FaqDTO> actualizar(@PathVariable Long id, @Valid @RequestBody FaqDTO dto) {
        return ResponseEntity.ok(faqService.actualizarFaq(id, dto));
    }

    /** DELETE /api/faqs/{id} */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        faqService.eliminarFaq(id);
        return ResponseEntity.noContent().build();
    }
}

package com.susbsonic.usuarios.Controller;

import com.susbsonic.usuarios.models.DTO.EntradaDTO;
import com.susbsonic.usuarios.models.DTO.EntradaRequestDTO;
import com.susbsonic.usuarios.Services.EntradaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entradas")
@RequiredArgsConstructor
public class EntradaController {

    private final EntradaService entradaService;

    /** GET /api/entradas → Todas las entradas */
    @GetMapping
    public ResponseEntity<List<EntradaDTO>> getAll() {
        return ResponseEntity.ok(entradaService.getAllEntradas());
    }

    /** GET /api/entradas/disponibles → Solo disponibles */
    @GetMapping("/disponibles")
    public ResponseEntity<List<EntradaDTO>> getDisponibles() {
        return ResponseEntity.ok(entradaService.getEntradasDisponibles());
    }

    /** GET /api/entradas/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<EntradaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(entradaService.getEntradaById(id));
    }

    /** POST /api/entradas (ADMIN) */
    @PostMapping
    public ResponseEntity<EntradaDTO> crear(@Valid @RequestBody EntradaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(entradaService.crearEntrada(dto));
    }

    /** PUT /api/entradas/{id} (ADMIN) */
    @PutMapping("/{id}")
    public ResponseEntity<EntradaDTO> actualizar(@PathVariable Long id,
                                                  @Valid @RequestBody EntradaRequestDTO dto) {
        return ResponseEntity.ok(entradaService.actualizarEntrada(id, dto));
    }

    /** DELETE /api/entradas/{id} (ADMIN) */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        entradaService.eliminarEntrada(id);
        return ResponseEntity.noContent().build();
    }
}

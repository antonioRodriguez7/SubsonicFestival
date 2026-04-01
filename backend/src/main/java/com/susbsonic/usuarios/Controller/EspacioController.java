package com.susbsonic.usuarios.Controller;

import com.susbsonic.usuarios.models.DTO.EspacioDTO;
import com.susbsonic.usuarios.models.DTO.EspacioRequestDTO;
import com.susbsonic.usuarios.Services.EspacioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/espacios")
@RequiredArgsConstructor
public class EspacioController {

    private final EspacioService espacioService;

    /** GET /api/espacios → Todos los espacios */
    @GetMapping
    public ResponseEntity<List<EspacioDTO>> getAll() {
        return ResponseEntity.ok(espacioService.getAllEspacios());
    }

    /** GET /api/espacios/disponibles → Disponibilidad = "Disponible" */
    @GetMapping("/disponibles")
    public ResponseEntity<List<EspacioDTO>> getDisponibles() {
        return ResponseEntity.ok(espacioService.getEspaciosDisponibles());
    }

    /** GET /api/espacios/reservados → Disponibilidad = "Reservado" */
    @GetMapping("/reservados")
    public ResponseEntity<List<EspacioDTO>> getReservados() {
        return ResponseEntity.ok(espacioService.getEspaciosReservados());
    }

    /** GET /api/espacios/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<EspacioDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(espacioService.getEspacioById(id));
    }

    /** POST /api/espacios */
    @PostMapping
    public ResponseEntity<EspacioDTO> crear(@Valid @RequestBody EspacioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(espacioService.crearEspacio(dto));
    }

    /** PUT /api/espacios/{id} */
    @PutMapping("/{id}")
    public ResponseEntity<EspacioDTO> actualizar(@PathVariable Long id,
                                                   @Valid @RequestBody EspacioRequestDTO dto) {
        return ResponseEntity.ok(espacioService.actualizarEspacio(id, dto));
    }

    /** DELETE /api/espacios/{id} */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        espacioService.eliminarEspacio(id);
        return ResponseEntity.noContent().build();
    }
}

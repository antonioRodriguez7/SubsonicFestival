package com.susbsonic.usuarios.Controller;

import com.susbsonic.usuarios.models.DTO.ServicioDTO;
import com.susbsonic.usuarios.models.DTO.ServicioRequestDTO;
import com.susbsonic.usuarios.Services.ServicioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
@RequiredArgsConstructor
public class ServicioController {

    private final ServicioService servicioService;

    /** GET /api/servicios → Todos los servicios */
    @GetMapping
    public ResponseEntity<List<ServicioDTO>> getAll() {
        return ResponseEntity.ok(servicioService.getAllServicios());
    }

    /**
     * GET /api/servicios/proveedor/{proveedorId}
     * Servicios de un proveedor concreto (el ID viene del backend de usuarios).
     */
    @GetMapping("/proveedor/{proveedorId}")
    public ResponseEntity<List<ServicioDTO>> getByProveedor(@PathVariable Long proveedorId) {
        return ResponseEntity.ok(servicioService.getServiciosByProveedor(proveedorId));
    }

    /** GET /api/servicios/espacio/{espacioId} → Servicios de un espacio */
    @GetMapping("/espacio/{espacioId}")
    public ResponseEntity<List<ServicioDTO>> getByEspacio(@PathVariable Long espacioId) {
        return ResponseEntity.ok(servicioService.getServiciosByEspacio(espacioId));
    }

    /** GET /api/servicios/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<ServicioDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(servicioService.getServicioById(id));
    }

    /** POST /api/servicios */
    @PostMapping
    public ResponseEntity<ServicioDTO> crear(@Valid @RequestBody ServicioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioService.crearServicio(dto));
    }

    /** PUT /api/servicios/{id} */
    @PutMapping("/{id}")
    public ResponseEntity<ServicioDTO> actualizar(@PathVariable Long id,
                                                   @Valid @RequestBody ServicioRequestDTO dto) {
        return ResponseEntity.ok(servicioService.actualizarServicio(id, dto));
    }

    /** DELETE /api/servicios/{id} */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicioService.eliminarServicio(id);
        return ResponseEntity.noContent().build();
    }
}

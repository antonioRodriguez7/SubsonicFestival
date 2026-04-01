package com.susbsonic.usuarios.Controller;

import com.susbsonic.usuarios.models.DTO.ArtistaDTO;
import com.susbsonic.usuarios.models.DTO.ArtistaRequestDTO;
import com.susbsonic.usuarios.Services.ArtistaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artistas")
@RequiredArgsConstructor
public class ArtistaController {

    private final ArtistaService artistaService;

    /** GET /api/artistas → Todos los artistas */
    @GetMapping
    public ResponseEntity<List<ArtistaDTO>> getAll() {
        return ResponseEntity.ok(artistaService.getAllArtistas());
    }

    /** GET /api/artistas?dia=Viernes+17+Julio → Filtrar por día */
    @GetMapping(params = "dia")
    public ResponseEntity<List<ArtistaDTO>> getByDia(@RequestParam String dia) {
        return ResponseEntity.ok(artistaService.getArtistasByDia(dia));
    }

    /** GET /api/artistas/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<ArtistaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(artistaService.getArtistaById(id));
    }

    /** POST /api/artistas (ADMIN) */
    @PostMapping
    public ResponseEntity<ArtistaDTO> crear(@Valid @RequestBody ArtistaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(artistaService.crearArtista(dto));
    }

    /** PUT /api/artistas/{id} (ADMIN) */
    @PutMapping("/{id}")
    public ResponseEntity<ArtistaDTO> actualizar(@PathVariable Long id,
                                                  @Valid @RequestBody ArtistaRequestDTO dto) {
        return ResponseEntity.ok(artistaService.actualizarArtista(id, dto));
    }

    /** DELETE /api/artistas/{id} (ADMIN) */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        artistaService.eliminarArtista(id);
        return ResponseEntity.noContent().build();
    }
}

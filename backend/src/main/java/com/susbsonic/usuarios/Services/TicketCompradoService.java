package com.susbsonic.usuarios.Services;

import com.susbsonic.usuarios.models.Entrada;
import com.susbsonic.usuarios.models.DAO.TicketComprados;
import com.susbsonic.usuarios.models.DAO.User;
import com.susbsonic.usuarios.models.DTO.TicketCompradoDTO;
import com.susbsonic.usuarios.Repositories.EntradaRepository;
import com.susbsonic.usuarios.Repositories.TicketCompradoRepository;
import com.susbsonic.usuarios.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketCompradoService {

    private final TicketCompradoRepository purchaseRepository;
    private final UserRepository userRepository;
    private final EntradaRepository entradaRepository;

    public TicketCompradoService(TicketCompradoRepository purchaseRepository,
                                 UserRepository userRepository,
                                 EntradaRepository entradaRepository) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.entradaRepository = entradaRepository;
    }

    private TicketCompradoDTO mapToDTO(TicketComprados purchase) {
        return TicketCompradoDTO.builder()
                .id(purchase.getId())
                .userId(purchase.getUser().getId())
                .ticketId(purchase.getEntrada().getId())
                .cantidad(purchase.getQuantity())
                .precioTotal(purchase.getTotalPrice())
                .comprasDate(purchase.getPurchaseDate())
                .build();
    }

    /**
     * Lógica principal para comprar una entrada.
     * Usamos @Transactional para que si algo falla se deshaga toda la operación.
     */
    @Transactional
    public TicketCompradoDTO buyTicket(TicketCompradoDTO dto) {
        // 1. Buscamos al usuario y al tipo de entrada
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Entrada entrada = entradaRepository.findById(dto.getTicketId())
                .orElseThrow(() -> new RuntimeException("Entrada no encontrada"));

        // 2. Comprobamos si la entrada está disponible
        if (entrada.getEstado() == Entrada.EstadoEntrada.agotado) {
            throw new RuntimeException("La entrada '" + entrada.getNombre() + "' está agotada.");
        }

        // 3. Calculamos el precio total
        Double total = entrada.getPrecio().doubleValue() * dto.getCantidad();

        // 4. Creamos el registro de la compra
        TicketComprados purchase = TicketComprados.builder()
                .user(user)
                .entrada(entrada)
                .quantity(dto.getCantidad())
                .totalPrice(total)
                .purchaseDate(LocalDateTime.now())
                .build();

        TicketComprados savedPurchase = purchaseRepository.save(purchase);

        return mapToDTO(savedPurchase);
    }

    /**
     * Devuelve el historial de compras de un usuario.
     */
    public List<TicketCompradoDTO> getPurchasesByUser(Long userId) {
        return purchaseRepository.findByUserId(userId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Devuelve TODAS las compras (Solo para el Admin).
     */
    public List<TicketCompradoDTO> getAllPurchases() {
        return purchaseRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
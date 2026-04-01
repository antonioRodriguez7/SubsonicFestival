package com.susbsonic.usuarios.Config;

import com.susbsonic.usuarios.models.*;
import com.susbsonic.usuarios.models.DAO.User;
import com.susbsonic.usuarios.Repositories.*;
import com.susbsonic.usuarios.Repositories.ArtistaRepository;
import com.susbsonic.usuarios.Repositories.EntradaRepository;
import com.susbsonic.usuarios.Repositories.EspacioRepository;
import com.susbsonic.usuarios.Repositories.FaqRepository;
import com.susbsonic.usuarios.Repositories.ServicioRepository;
import com.susbsonic.usuarios.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * DataSeeder — Se ejecuta automáticamente al arrancar la aplicación.
 * Solo inserta datos si las tablas están vacías, para no duplicar en reinicios.
 */
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    private final ArtistaRepository artistaRepository;
    private final EntradaRepository entradaRepository;
    private final EspacioRepository espacioRepository;
    private final FaqRepository faqRepository;
    private final ServicioRepository servicioRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        seedArtistas();
        seedEntradas();
        seedEspacios();
        seedFaqs();
        seedServicios();
        seedUsuarios();
        log.info("✅ DataSeeder completado.");
    }

    // ══════════════════════════════════════════════════════════
    // ARTISTAS
    // ══════════════════════════════════════════════════════════
    private void seedArtistas() {
        if (artistaRepository.count() > 0) return;
        log.info("🎤 Insertando artistas...");

        artistaRepository.saveAll(List.of(
            // Viernes 17 Julio
            artista("Bad Bunny",        "Viernes 17 Julio", "/artists/badbunny.avif",    "https://open.spotify.com/intl-es/artist/4q3ewBCX7sLwd24euuV69X"),
            artista("Rosalía",          "Viernes 17 Julio", "/artists/rosalia.jpg",       "https://open.spotify.com/intl-es/artist/7ltDVBr6mKbRvohxheJ9h1"),
            artista("Martín Garrix",    "Viernes 17 Julio", "/artists/martingarrix.jpg",  "https://open.spotify.com/intl-es/artist/60d24wfXkVzDSfLS6hyCjZ"),
            artista("Quevedo",          "Viernes 17 Julio", "/artists/quevedo.jpg",       "https://open.spotify.com/intl-es/artist/52iwsT98xCoGgiGntTiR7K"),
            artista("Bizarrap",         "Viernes 17 Julio", "/artists/biza.webp",         "https://open.spotify.com/intl-es/artist/716NhGYqD1jl2wI1Qkgq36"),
            artista("Charlotte de Witte","Viernes 17 Julio","/artists/charlotte.jpg",    "https://open.spotify.com/intl-es/artist/1lJhME1ZpzsEa5M0wW6Mso"),
            artista("Saiko",            "Viernes 17 Julio", "/artists/saiko.jpg",         "https://open.spotify.com/intl-es/artist/2O8vbr4RYPpk6MRA4fio7u"),
            artista("Trueno",           "Viernes 17 Julio", "/artists/trueno.jpg",        "https://open.spotify.com/intl-es/artist/2x7PC78TmgqpEIjaGAZ0Oz"),
            artista("Anuel AA",         "Viernes 17 Julio", "/artists/anuel.jpg",         "https://open.spotify.com/search/Anuel%20AA"),
            artista("Amelie Lens",      "Viernes 17 Julio", "/artists/amelielens.webp",   "https://open.spotify.com/search/Amelie%20Lens"),
            artista("Mora",             "Viernes 17 Julio", "/artists/mora.jpg",          "https://open.spotify.com/search/Mora"),
            artista("Don Diablo",       "Viernes 17 Julio", "/artists/dondiablo.jpg",     "https://open.spotify.com/search/Don%20Diablo"),
            artista("Vintage Culture",  "Viernes 17 Julio", "/artists/vintage.jpg",       "https://open.spotify.com/search/Vintage%20Culture"),
            // Sábado 18 Julio
            artista("Feid",            "Sábado 18 Julio", "/artists/feid.webp",          "https://open.spotify.com/search/Feid"),
            artista("David Guetta",    "Sábado 18 Julio", "/artists/davidguetta.jpeg",   "https://open.spotify.com/search/David%20Guetta"),
            artista("Karol G",         "Sábado 18 Julio", "/artists/karolg.jpg",         "https://open.spotify.com/search/Karol%20G"),
            artista("Myke Towers",     "Sábado 18 Julio", "/artists/myketowers.webp",    "https://open.spotify.com/search/Myke%20Towers"),
            artista("Carl Cox",        "Sábado 18 Julio", "/artists/carlcox.jpg",        "https://open.spotify.com/search/Carl%20Cox"),
            artista("Tale Of Us",      "Sábado 18 Julio", "/artists/tale.jpg",           "https://open.spotify.com/search/Tale%20Of%20Us"),
            artista("Dellafuente",     "Sábado 18 Julio", "/artists/dellafuente.avif",   "https://open.spotify.com/search/Dellafuente"),
            artista("Peggy Gou",       "Sábado 18 Julio", "/artists/peggy.webp",         "https://open.spotify.com/search/Peggy%20Gou"),
            artista("Anyma",           "Sábado 18 Julio", "/artists/anyma.png",          "https://open.spotify.com/search/Anyma"),
            artista("Marco Trujillo",  "Sábado 18 Julio", "/artists/trujillo.webp",      "https://open.spotify.com/search/Marco%20Trujillo"),
            artista("Aidan DJ",        "Sábado 18 Julio", "/artists/aidan.webp",         "https://open.spotify.com/search/Aidan%20DJ"),
            // Domingo 19 Julio
            artista("J Balvin",        "Domingo 19 Julio", "/artists/jbalvin.jpg",       "https://open.spotify.com/search/J%20Balvin"),
            artista("Rauw Alejandro",  "Domingo 19 Julio", "/artists/rauw.webp",         "https://open.spotify.com/search/Rauw%20Alejandro"),
            artista("Eladio Carrión",  "Domingo 19 Julio", "/artists/eladio.jpeg",       "https://open.spotify.com/search/Eladio%20Carri%C3%B3n"),
            artista("Steve Aoki",      "Domingo 19 Julio", "/artists/aoki.jpg",          "https://open.spotify.com/search/Steve%20Aoki"),
            artista("Central Cee",     "Domingo 19 Julio", "/artists/centralcee.jpg",    "https://open.spotify.com/search/Central%20Cee"),
            artista("Bad Gyal",        "Domingo 19 Julio", "/artists/badgyal.webp",      "https://open.spotify.com/search/Bad%20Gyal"),
            artista("Duki",            "Domingo 19 Julio", "/artists/duki.jpg",          "https://open.spotify.com/search/Duki"),
            artista("Solomun",         "Domingo 19 Julio", "/artists/solomun.jpg",       "https://open.spotify.com/search/Solomun"),
            artista("Alesso",          "Domingo 19 Julio", "/artists/alesso.jpg",        "https://open.spotify.com/search/Alesso")
        ));
    }

    private Artista artista(String nombre, String dia, String img, String spoty) {
        return Artista.builder().nombre(nombre).dia(dia).img(img).spoty(spoty).build();
    }

    // ══════════════════════════════════════════════════════════
    // ENTRADAS
    // ══════════════════════════════════════════════════════════
    private void seedEntradas() {
        if (entradaRepository.count() > 0) return;
        log.info("🎟️ Insertando entradas...");

        entradaRepository.saveAll(List.of(
            Entrada.builder()
                .nombre("ABONO GENERAL")
                .precio(new BigDecimal("72.50"))
                .descripcion("Acceso a todos los escenarios durante los 3 días del festival.")
                .etiqueta("MÁS VENDIDO")
                .tipoEtiqueta("popular")
                .estado(Entrada.EstadoEntrada.disponible)
                .img("/imgsTickets/ticketNormal.jpg")
                .build(),
            Entrada.builder()
                .nombre("ABONO VIP")
                .precio(new BigDecimal("155.00"))
                .descripcion("Zona VIP exclusiva, acceso prioritario y barra privada.")
                .etiqueta("MUY LIMITADO")
                .tipoEtiqueta("limitado")
                .estado(Entrada.EstadoEntrada.disponible)
                .img("/imgsTickets/ticketVIP.jpg")
                .build(),
            Entrada.builder()
                .nombre("DREAM VIP")
                .precio(new BigDecimal("300.00"))
                .descripcion("Experiencia premium completa, backstage y catering exclusivo.")
                .etiqueta("NOVEDAD")
                .tipoEtiqueta("nuevo")
                .estado(Entrada.EstadoEntrada.agotado)
                .img("/imgsTickets/ticketDreamVIP.jpg")
                .build()
        ));
    }

    // ══════════════════════════════════════════════════════════
    // ESPACIOS
    // ══════════════════════════════════════════════════════════
    private void seedEspacios() {
        if (espacioRepository.count() > 0) return;
        log.info("🏟️ Insertando espacios...");

        espacioRepository.saveAll(List.of(
            espacio("Zona Velar",        "Norte",  "Terreno llano cubierto con carpa",         "Acceso principal norte del recinto", "500m²",  new BigDecimal("2500.00"), "Espacio premium en zona de máximo tránsito, con carpa que garantiza protección frente a las condiciones meteorológicas.", "20 stands",           List.of("Electricidad","Agua corriente","Zona de carga/descarga","Iluminación nocturna"),                    "Disponible", "/espacios/foodcourt.jpg",    null, null),
            espacio("Zona Paseo Central","Centro", "Avenida peatonal pavimentada",              "Eje central del recinto",            "300m²",  new BigDecimal("1800.00"), "Paseo principal de alto tránsito con suelo pavimentado y señalización visual estratégica.",                               "15 stands",           List.of("Electricidad","WiFi","Seguridad 24h","Almacén cercano"),                                               "Disponible", "/espacios/merchandising.jpg", null, null),
            espacio("Zona Relax",        "Este",   "Terreno ajardinado con sombra natural",     "Área verde lateral este",            "800m²",  new BigDecimal("3200.00"), "Área verde con árboles y vegetación que proporciona sombra natural, ideal para experiencias al aire libre.",             "10 espacios grandes", List.of("Electricidad","Sombra natural","Zona WiFi","Asientos incluidos"),                                        "Reservado",  "/espacios/chillout.jpg",     "Green Bites",      "Comida"),
            espacio("Zona VIP",          "Centro", "Recinto cerrado con acceso controlado",     "Zona exclusiva central del recinto", "150m²",  new BigDecimal("4500.00"), "Espacio exclusivo vallado con acceso restringido por pulsera, ambiente premium y atención personalizada.",                "5 barras",            List.of("Electricidad","Agua","Cámaras frigoríficas","Sistema de sonido","Iluminación especial"),                   "Disponible", "/espacios/vipbar.jpg",       null, null),
            espacio("Zona Innova",       "Sur",    "Pabellón cubierto climatizado",             "Pabellón sur - Área Innovación",     "600m²",  new BigDecimal("2800.00"), "Pabellón cerrado con climatización, perfecto para instalaciones tecnológicas y activaciones de alto impacto.",             "8 stands grandes",    List.of("Electricidad de alta potencia","WiFi fibra óptica","Climatización","Proyectores"),                          "Disponible", "/espacios/gaming.jpg",       null, null),
            espacio("Zona Oeste",        "Oeste",  "Callejón urbano con suelo de adoquín",      "Lateral oeste del recinto",          "400m²",  new BigDecimal("2000.00"), "Callejón con estética urbana, suelo de adoquín y alto flujo de asistentes procedentes del escenario principal.",          "12 food trucks",      List.of("Electricidad","Agua","Sistema de extracción","Zona de comensales"),                                        "Disponible", "/espacios/streetfood.jpg",   null, null),
            espacio("Zona Boutique",     "Norte",  "Espacio acotado con iluminación especial",  "Entrada secundaria norte",           "120m²",  new BigDecimal("1500.00"), "Rincón exclusivo con iluminación cálida y delimitación visual, pensado para marcas de carácter premium.",                "6 stands",            List.of("Electricidad","Espejos","Iluminación profesional","Aire acondicionado"),                                   "Disponible", "/espacios/beauty.jpg",       null, null),
            espacio("Zona Stage",        "Sur",    "Tarima elevada con gradas laterales",       "Extremo sur del recinto",            "200m²",  new BigDecimal("5000.00"), "Escenario secundario elevado con gradas a ambos lados, máxima visibilidad desde cualquier ángulo del recinto.",           "1 escenario completo", List.of("Sistema de sonido completo","Iluminación profesional","Backstage","Generador propio"),                    "Reservado",  "/espacios/stage.jpg",        "SoundWave Events", "Entretenimiento")
        ));
    }

    private Espacio espacio(String nombre, String zona, String caracteristica, String lugar,
                             String tamano, BigDecimal precio, String descripcion, String capacidad,
                             List<String> servicios, String disponibilidad, String imagen,
                             String negocioNombre, String negocioCategoria) {
        return Espacio.builder()
                .nombre(nombre).zonaGeneral(zona).caracteristica(caracteristica)
                .evento("Subsonic Festival 2026").lugar(lugar).tamano(tamano)
                .precio(precio).descripcion(descripcion).capacidad(capacidad)
                .servicios(servicios).disponibilidad(disponibilidad).imagen(imagen)
                .negocioNombre(negocioNombre).negocioCategoria(negocioCategoria)
                .build();
    }

    // ══════════════════════════════════════════════════════════
    // FAQS
    // ══════════════════════════════════════════════════════════
    private void seedFaqs() {
        if (faqRepository.count() > 0) return;
        log.info("❓ Insertando FAQs...");

        faqRepository.saveAll(List.of(
            // FAQs para usuarios
            faq("¿Cómo compro entradas?",         "Desde la sección Entradas puedes seleccionar el abono y completar la compra.",                   Faq.TargetRole.USUARIO,   1),
            faq("¿Puedo devolver mi entrada?",    "Las entradas pueden cancelarse según las condiciones indicadas en Política y Privacidad.",         Faq.TargetRole.USUARIO,   2),
            faq("¿Hay restricciones de edad?",    "Depende de la normativa del evento. Revisa la sección Info.",                                     Faq.TargetRole.USUARIO,   3),
            faq("¿Qué puedo llevar al festival?", "Consulta la lista de objetos permitidos en Preguntas Frecuentes.",                                Faq.TargetRole.USUARIO,   4),
            faq("¿Dónde se celebra y cómo llego?","La ubicación y accesos están en la sección Info/Mapa.",                                           Faq.TargetRole.USUARIO,   5),
            faq("¿Qué pasa si llueve?",           "El festival se celebra salvo condiciones extremas. Sigue comunicaciones oficiales.",               Faq.TargetRole.USUARIO,   6),
            // FAQs para proveedores
            faq("¿Cómo me registro como proveedor?", "En Registro elige Proveedor y añade la descripción del servicio.",                             Faq.TargetRole.PROVEEDOR, 1),
            faq("¿Cómo solicito un espacio?",        "Desde Proveedores podrás iniciar el proceso de solicitud.",                                    Faq.TargetRole.PROVEEDOR, 2),
            faq("¿Qué tipos de proveedor se aceptan?","Foodtruck, merchandising, servicios técnicos, etc.",                                          Faq.TargetRole.PROVEEDOR, 3),
            faq("¿Cómo se valida mi solicitud?",     "El equipo revisa tu propuesta y contacta contigo.",                                            Faq.TargetRole.PROVEEDOR, 4),
            faq("¿Hay cuota o comisión?",            "Depende del tipo de servicio. Se especifica en las condiciones de proveedores.",                Faq.TargetRole.PROVEEDOR, 5),
            faq("¿Qué documentación necesito?",      "Licencias, seguro, certificados sanitarios (si aplica) y datos fiscales.",                     Faq.TargetRole.PROVEEDOR, 6)
        ));
    }

    private Faq faq(String pregunta, String respuesta, Faq.TargetRole role, int orden) {
        return Faq.builder().pregunta(pregunta).respuesta(respuesta).targetRole(role).orden(orden).build();
    }

    // ══════════════════════════════════════════════════════════
    // SERVICIOS
    // ══════════════════════════════════════════════════════════
    private void seedServicios() {
        if (servicioRepository.count() > 0) return;
        log.info("🍹 Insertando servicios...");

        espacioRepository.findAll().stream()
            .filter(e -> e.getNombre().equals("Zona Velar"))
            .findFirst()
            .ifPresent(zonaVelar -> servicioRepository.save(
                Servicio.builder()
                    .espacio(zonaVelar)
                    .nombre("Barra Fusión")
                    .tipo("Bebidas")
                    .descripcion("Servicio de cócteles y bebidas sin alcohol durante todo el festival.")
                    .fechas("17-20 julio")
                    .build()
            ));
    }

    // ══════════════════════════════════════════════════════════
    // USUARIOS DE PRUEBA
    // ══════════════════════════════════════════════════════════
    private void seedUsuarios() {
        if (userRepository.count() > 0) return;
        log.info("👤 Insertando usuarios de prueba...");

        userRepository.saveAll(List.of(
            User.builder()
                .name("Carlos").surname("García").username("carlos_g")
                .email("carlos@subsonic.com").password(passwordEncoder.encode("cliente123"))
                .role(RoleList.ROLE_USER).isAdmin(false).verified(true)
                .bio("Asistente habitual al festival desde 2021. Fan del techno y el reggaeton.")
                .build(),
            User.builder()
                .name("Laura").surname("Martínez").username("laura_m")
                .email("laura@foodtrucks.com").password(passwordEncoder.encode("proveedor123"))
                .role(RoleList.ROLE_PROVEEDOR).isAdmin(false).verified(true)
                .bio("Propietaria de Food Trucks & Co. Especializada en street food de calidad.")
                .build(),
            User.builder()
                .name("Admin").surname("Subsonic").username("admin_subsonic")
                .email("admin@subsonic.com").password(passwordEncoder.encode("admin123"))
                .role(RoleList.ROLE_ADMIN).isAdmin(true).verified(true)
                .bio("Administrador general del Subsonic Festival 2026.")
                .build()
        ));
    }
}

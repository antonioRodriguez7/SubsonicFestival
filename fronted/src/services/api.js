/* Importamos ambos backends y exportamos el que queremos usar */
import * as fake from "./fakeBackend";
import * as real from "./realBackend";

// ⚡ Cambia a true para usar datos mock (sin backend)
const usarBackendFalso = false;

const backend = usarBackendFalso ? fake : real;

// ── Contenido público ─────────────────────────────────────
export const getArtistas              = backend.getArtistas;
export const getEntradas              = backend.getEntradas;
export const getFaqsUsuarios          = backend.getFaqsUsuarios;
export const getFaqsProveedores       = backend.getFaqsProveedores;

// ── Espacios ──────────────────────────────────────────────
export const getEspacios                    = backend.getEspacios;
export const getEspaciosDisponibles         = backend.getEspaciosDisponibles;
export const getEspaciosContratadosProveedor = backend.getEspaciosContratadosProveedor;

// ── Servicios ─────────────────────────────────────────────
export const getServiciosProveedor    = backend.getServiciosProveedor;

// ── Auth ──────────────────────────────────────────────────
export const login       = backend.login;
export const register    = backend.register;
export const loginGoogle = backend.loginGoogle;
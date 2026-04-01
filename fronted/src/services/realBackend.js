const BASE_URL = "http://localhost:8080/api";

// ─── Helper: lanza error con mensaje si la respuesta no es ok ─────────────────
async function fetchJSON(url, options = {}) {
  const response = await fetch(url, options);
  if (!response.ok) {
    throw new Error(`Error ${response.status}: ${response.statusText} — ${url}`);
  }
  return response.json();
}

// ══════════════════════════════════════════════════════════
// ARTISTAS
// GET /api/artistas
// ══════════════════════════════════════════════════════════
export function getArtistas() {
  return fetchJSON(`${BASE_URL}/artistas`);
}

// ══════════════════════════════════════════════════════════
// ENTRADAS
// GET /api/entradas
// ══════════════════════════════════════════════════════════
export function getEntradas() {
  return fetchJSON(`${BASE_URL}/entradas`);
}

// ══════════════════════════════════════════════════════════
// FAQS
// GET /api/faqs/usuarios
// GET /api/faqs/proveedores
// ══════════════════════════════════════════════════════════
export function getFaqsUsuarios() {
  return fetchJSON(`${BASE_URL}/faqs/usuarios`);
}

export function getFaqsProveedores() {
  return fetchJSON(`${BASE_URL}/faqs/proveedores`);
}

// ══════════════════════════════════════════════════════════
// ESPACIOS
// GET /api/espacios              → todos los espacios (admin)
// GET /api/espacios/disponibles  → solo disponibles (proveedores)
// GET /api/espacios/reservados   → solo reservados (contratados)
// ══════════════════════════════════════════════════════════
export function getEspacios() {
  return fetchJSON(`${BASE_URL}/espacios`);
}

export function getEspaciosDisponibles() {
  return fetchJSON(`${BASE_URL}/espacios/disponibles`);
}

// Espacios contratados por un proveedor concreto (los que están Reservados)
// Por ahora devuelve todos los reservados; cuando haya auth se filtrará por proveedor
export function getEspaciosContratadosProveedor() {
  return fetchJSON(`${BASE_URL}/espacios/reservados`);
}

// ══════════════════════════════════════════════════════════
// SERVICIOS
// GET /api/servicios/proveedor/{id}  → servicios de un proveedor
// ══════════════════════════════════════════════════════════

// Devuelve los servicios de un proveedor dado su ID
// Si no se pasa ID, devuelve todos (para cuando no hay auth todavía)
export function getServiciosProveedor(proveedorId) {
  if (proveedorId) {
    return fetchJSON(`${BASE_URL}/servicios/proveedor/${proveedorId}`);
  }
  return fetchJSON(`${BASE_URL}/servicios`);
}

// ══════════════════════════════════════════════════════════
// AUTH
// POST /api/auth/login
// POST /api/auth/register
// POST /api/auth/google
// ══════════════════════════════════════════════════════════
export function login(email, password) {
  return fetchJSON(`${BASE_URL}/auth/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ email, password }),
  });
}

export function register(datos) {
  return fetchJSON(`${BASE_URL}/auth/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(datos),
  });
}

export function loginGoogle(token) {
  return fetchJSON(`${BASE_URL}/auth/google`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ token }),
  });
}
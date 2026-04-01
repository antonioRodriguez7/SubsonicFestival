import React, { createContext, useContext, useState, useEffect } from 'react';

const AuthContext = createContext(null);

/**
 * Proveedor de autenticación global.
 * Guarda el usuario en localStorage para persistir entre recargas.
 */
export function AuthProvider({ children }) {
  const [usuario, setUsuario] = useState(() => {
    try {
      const guardado = localStorage.getItem('subsonic_usuario');
      return guardado ? JSON.parse(guardado) : null;
    } catch {
      return null;
    }
  });

  // Guarda el usuario en localStorage cada vez que cambia
  useEffect(() => {
    if (usuario) {
      localStorage.setItem('subsonic_usuario', JSON.stringify(usuario));
    } else {
      localStorage.removeItem('subsonic_usuario');
    }
  }, [usuario]);

  /**
   * Inicia sesión guardando los datos recibidos del backend.
   * @param {Object} datosBackend - respuesta del endpoint /api/auth/login
   */
  function iniciarSesion(datosBackend) {
    setUsuario({
      id:       datosBackend.id,
      name:     datosBackend.name,
      surname:  datosBackend.surname,
      username: datosBackend.username,
      email:    datosBackend.email,
      bio:      datosBackend.bio,
      role:     datosBackend.role,   // ROLE_USER | ROLE_ADMIN | ROLE_PROVEEDOR
      token:    datosBackend.token,
    });
  }

  function cerrarSesion() {
    setUsuario(null);
  }

  return (
    <AuthContext.Provider value={{ usuario, iniciarSesion, cerrarSesion }}>
      {children}
    </AuthContext.Provider>
  );
}

/** Hook cómodo para usar el contexto */
export function useAuth() {
  return useContext(AuthContext);
}

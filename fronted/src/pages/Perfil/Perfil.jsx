import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';
import './Perfil.css';

function Perfil() {
  const navigate = useNavigate();
  const { usuario, cerrarSesion } = useAuth();

  // Si no hay sesión, redirige al login
  if (!usuario) {
    navigate('/login');
    return null;
  }

  // Mapeamos el rol a un tipo legible
  const rolMap = {
    ROLE_USER:      'cliente',
    ROLE_ADMIN:     'administrador',
    ROLE_PROVEEDOR: 'proveedor',
  };
  const tipoUsuario = rolMap[usuario.role] ?? 'cliente';

  // Estado del formulario inicializado con los datos reales del usuario
  const [perfil, setPerfil] = useState({
    nombre:      usuario.name     ?? '',
    apellidos:   usuario.surname  ?? '',
    username:    usuario.username ?? '',
    descripcion: usuario.bio      ?? '',
    email:       usuario.email    ?? '',
  });

  const handleChange = (field, value) =>
    setPerfil(prev => ({ ...prev, [field]: value }));

  const nombreCompleto = [perfil.nombre, perfil.apellidos].filter(Boolean).join(' ');

  function handleLogout() {
    cerrarSesion();
    navigate('/login');
  }

  return (
    <div className="perfil-page">

      {/* CABECERA PERFIL */}
      <div className="perfil-nav">
        <button className="btn-atras" onClick={() => navigate('/')}>
          ← Atrás
        </button>

        <img
          src="/logoPI.png"
          alt="Logo"
          className="perfil-logo"
          style={{ cursor: 'pointer' }}
          onClick={() => navigate('/')}
        />

        <button className="btn-logout-nav" onClick={handleLogout}>
          Cerrar Sesión
        </button>
      </div>

      <div className="perfil-container">

        {/* PARTE SUPERIOR */}
        <div className="perfil-top-section">

          {/* IZQUIERDA: avatar + nombre */}
          <div className="perfil-left">
            <div className="avatar-circle">
              <span>{perfil.nombre ? perfil.nombre[0].toUpperCase() : 'U'}</span>
            </div>

            <h3 className="perfil-username">
              {nombreCompleto || 'Nombre Usuario'}
            </h3>

            {perfil.username && (
              <p className="perfil-at-username">@{perfil.username}</p>
            )}

            <p className="perfil-role-badge" style={{ color: '#df188a', fontWeight: 'bold', fontSize: '13px', marginTop: '4px' }}>
              {tipoUsuario.toUpperCase()}
            </p>

            <p className="perfil-desc-text">
              {perfil.descripcion || <span style={{ color: '#555', fontStyle: 'italic' }}>Sin descripción</span>}
            </p>
          </div>

          {/* DERECHA: formulario de edición */}
          <div className="perfil-right">
            <form className="perfil-form" onSubmit={e => e.preventDefault()}>
              <input
                type="text"
                placeholder="Nombre"
                value={perfil.nombre}
                onChange={e => handleChange('nombre', e.target.value)}
              />
              <input
                type="text"
                placeholder="Apellidos"
                value={perfil.apellidos}
                onChange={e => handleChange('apellidos', e.target.value)}
              />
              <input
                type="text"
                placeholder="Nombre de usuario"
                value={perfil.username}
                onChange={e => handleChange('username', e.target.value)}
              />
              <input
                type="text"
                placeholder="Descripción"
                value={perfil.descripcion}
                onChange={e => handleChange('descripcion', e.target.value)}
              />
              <input
                type="email"
                placeholder="Email"
                value={perfil.email}
                onChange={e => handleChange('email', e.target.value)}
              />

              <button type="button" className="btn-guardar">
                Guardar Cambios
              </button>
            </form>
          </div>
        </div>

        {/* DIVISOR */}
        <hr className="perfil-divider" />

        {/* SECCIÓN INFERIOR según rol */}
        <div className="perfil-bottom-section">
          {(tipoUsuario === 'administrador' || tipoUsuario === 'proveedor') ? (
            <div className="panel-admin-wrapper">
              <h3 className="section-title">
                {tipoUsuario === 'administrador' ? 'Administración' : 'Panel de Proveedor'}
              </h3>
              <p className="perfil-desc-text" style={{ marginBottom: '24px' }}>
                {tipoUsuario === 'administrador'
                  ? 'Accede al panel de administración para gestionar artistas, entradas y espacios.'
                  : 'Accede a tu panel de proveedor para gestionar tus espacios y servicios.'}
              </p>
              <button
                className="btn-panel-admin"
                onClick={() => navigate(
                  tipoUsuario === 'administrador' ? '/perfil-admin' : '/perfil-proveedor'
                )}
              >
                {tipoUsuario === 'administrador' ? 'Panel de Administración' : 'Panel de Proveedor'}
              </button>
            </div>
          ) : (
            <>
              <h3 className="section-title">Entradas adquiridas</h3>
              <div className="productos-grid">
                <div className="producto-card">
                  <div className="producto-img">🎟️</div>
                  <p>Abono 3 Días</p>
                </div>
                <div className="producto-card">
                  <div className="producto-img">⭐</div>
                  <p>Pase VIP</p>
                </div>
                <div className="producto-card empty">
                  <p>+</p>
                </div>
              </div>
            </>
          )}
        </div>

      </div>
    </div>
  );
}

export default Perfil;
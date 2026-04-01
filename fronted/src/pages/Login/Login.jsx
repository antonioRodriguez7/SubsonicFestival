import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';
import { login } from '../../services/api';
import './Login.css';

function Login() {
  const navigate = useNavigate();
  const { iniciarSesion } = useAuth();

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [cargando, setCargando] = useState(false);

  async function handleLogin(e) {
    e.preventDefault();
    setError('');
    setCargando(true);

    try {
      const datos = await login(email, password);
      iniciarSesion(datos); // guarda en contexto + localStorage

      // Todos van al perfil donde verán sus datos y botones específicos
      navigate('/perfil');
    } catch (err) {
      setError('Email o contraseña incorrectos');
    } finally {
      setCargando(false);
    }
  }

  return (
    <div className="login-container">

      {/* MITAD IZQUIERDA */}
      <div className="login-left">

        {/* Logo vuelve al home */}
        <div className="login-logo" onClick={() => navigate('/')}>
          <img src="/logoPI.png" alt="Logo Subsonic" />
        </div>

        <div className="login-box">
          <h2>Iniciar Sesión</h2>
          <p>Bienvenido de nuevo al festival</p>

          <form className="login-form" onSubmit={handleLogin}>
            <div className="input-group">
              <label>Email</label>
              <input
                type="email"
                placeholder="Introduce tu email..."
                value={email}
                onChange={e => setEmail(e.target.value)}
                required
              />
            </div>

            <div className="input-group">
              <label>Contraseña</label>
              <input
                type="password"
                placeholder="••••••••"
                value={password}
                onChange={e => setPassword(e.target.value)}
                required
              />
            </div>

            {error && (
              <p style={{ color: '#ff4d6d', fontSize: '13px', textAlign: 'center', marginBottom: '8px' }}>
                {error}
              </p>
            )}

            <button
              type="submit"
              className="btn-entrar"
              disabled={cargando}
            >
              {cargando ? 'Entrando...' : 'ENTRAR'}
            </button>
          </form>

          <div className="login-links">
            <a href="#olvido" className="link-olvido">
              ¿Olvidaste la contraseña?
            </a>

            <p className="link-registro">
              ¿No tienes cuenta?
              <span
                onClick={() => navigate('/registro')}
                style={{ cursor: 'pointer', color: '#df188a', fontWeight: 'bold' }}
              >
                {' '}Regístrate aquí
              </span>
            </p>
          </div>
        </div>
      </div>

      {/* MITAD DERECHA */}
      <div className="login-right"></div>

    </div>
  );
}

export default Login;
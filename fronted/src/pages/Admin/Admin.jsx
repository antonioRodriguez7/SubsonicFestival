import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getArtistas } from '../../services/api';
import './Admin.css';

function Admin() {

    const navigate = useNavigate();
    const [artistas, setArtistas] = useState([]);

    // Cargar artistas al montar el componente
    useEffect(() => {
        getArtistas().then(data => {
            setArtistas(data);
        }).catch(err => {
            console.error("Error al cargar los artistas:", err);
        });
    }, []);

    return (
        <div className="admin-wrapper">

            {/* MENÚ LATERAL */}
            <aside className="admin-sidebar">

                <div
                    className="admin-logo-container"
                    onClick={() => navigate('/')}
                    style={{ cursor: 'pointer' }}
                >
                    <img src="/logoPI.png" alt="Logo" className="admin-logo" />
                    <p className="admin-badge">ADMIN</p>
                </div>

                <nav className="admin-nav">
                    <button className="admin-nav-btn active">ARTISTA</button>
                    <button className="admin-nav-btn">ESPACIOS</button>
                    <button className="admin-nav-btn">HISTORIAL DE COMPRA</button>
                    <button className="admin-nav-btn">HISTORIAL DE PROVEEDORES</button>
                </nav>

                <div className="admin-sidebar-footer">
                    <button
                        className="admin-logout-btn"
                        onClick={() => navigate('/login')}
                    >
                        Cerrar Sesión
                    </button>
                </div>

            </aside>

            {/* ÁREA PRINCIPAL */}
            <main className="admin-main">

                <header className="admin-header">
                    <h2>Gestión de Artistas</h2>
                    <div className="admin-profile-circle" onClick={() => navigate('/perfil')} style={{cursor:'pointer'}}>A</div>
                </header>

                <div className="admin-content-box">
                    <h3 className="form-title">
                        AÑADIR NUEVO ARTISTA
                    </h3>

                    <form className="admin-form">
                        <div className="form-grid">
                            <input type="text" placeholder="Nombre completo" />
                            <input type="text" placeholder="Día de actuación" />
                            <input type="text" placeholder="URL de la imagen (/artists/...)" />
                            <input type="text" placeholder="URL de Spotify" />
                        </div>

                        <div className="form-actions">
                            <button
                                type="button"
                                className="btn-add-artist"
                            >
                                Añadir artista
                            </button>
                        </div>
                    </form>
                </div>

                {/* LISTADO DE ARTISTAS OBTENIDOS DEL BACKEND */}
                <div className="admin-content-box">
                    <h3 className="form-title">
                        ARTISTAS REGISTRADOS ({artistas.length})
                    </h3>
                    
                    <div className="admin-table-container">
                        <table className="admin-table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Imagen</th>
                                    <th>Nombre</th>
                                    <th>Día</th>
                                    <th>Spotify</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                {artistas.length > 0 ? (
                                    artistas.map(artista => (
                                        <tr key={artista.id}>
                                            <td>{artista.id}</td>
                                            <td>
                                                <img 
                                                    src={artista.img || "/logoPI.png"} 
                                                    alt={artista.nombre} 
                                                    style={{ width: "40px", height: "40px", borderRadius: "5px", objectFit: "cover" }} 
                                                />
                                            </td>
                                            <td style={{ fontWeight: 'bold' }}>{artista.nombre}</td>
                                            <td>{artista.dia}</td>
                                            <td>
                                                {artista.spoty ? (
                                                    <a href={artista.spoty} target="_blank" rel="noopener noreferrer" style={{color: '#1db954'}}>Abrir</a>
                                                ) : '-'}
                                            </td>
                                            <td>
                                                <button className="btn-editar">Editar</button>
                                                <button className="btn-eliminar">Borrar</button>
                                            </td>
                                        </tr>
                                    ))
                                ) : (
                                    <tr>
                                        <td colSpan="6" style={{ textAlign: 'center', padding: '20px', color: '#888' }}>
                                            No hay artistas cargados.
                                        </td>
                                    </tr>
                                )}
                            </tbody>
                        </table>
                    </div>
                </div>

            </main>
        </div>
    );
}

export default Admin;
import React, { useState } from 'react';
import '../Styles/AddCategory.css'; 

const AdminUser = () => {
  const [name, setName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [loading, setLoading] = useState(false);

  const loadUser = async () => {
    try {
      const response = await fetch('http://ec2-3-17-204-179.us-east-2.compute.amazonaws.com:8080/api/auth/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          name: name,
          lastName: lastName,
          email: email,
          password: password,
          rol: "ADMIN"
        }),
      });

      if (response.ok) {
        console.log('Usuario cargado correctamente.');
        alert('El usuario se ha cargado correctamente.');

        setName('');
        setLastName('');
        setEmail('');
        setPassword('');
      } else {
        console.error('Error al cargar usuario.');
      }
    } catch (error) {
      console.error('Ocurrió un error:', error);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    loadUser();
    setLoading(false);
  };

  return (
    <div className='container'>
      <form className="form" onSubmit={handleSubmit}>
        <h4>Crear administrador</h4>
        <div className="labelFlex">
          <label htmlFor="name">Nombre</label>
          <input className="input"
            placeholder="Nombre"
            type="text"
            id="name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </div>
        <div className="labelFlex">
          <label htmlFor="lastName">Apellido</label>
          <input className="input"
            placeholder="Apellido"
            type="text"
            id="lastName"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
          />
        </div>
        <div className="labelFlex">
          <label htmlFor="email">Email</label>
          <input className="input"
            placeholder="Email"
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div className="labelFlex">
          <label htmlFor="password">Contraseña</label>
          <input className="input"
            placeholder="Contraseña"
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <button className="primary-button login size" id="height1" type="submit">Agregar administrador</button>
      </form>

  </div>
  );
};

export default AdminUser;

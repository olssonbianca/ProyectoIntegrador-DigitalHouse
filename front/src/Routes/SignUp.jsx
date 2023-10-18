import React, { useState } from 'react';
import '../Styles/SignUp.css'

function Register() {
  const [name, setName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

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
          
        }),
      });

      if (response.ok) {
        // La solicitud se realizó con éxito
        // Aquí puedes realizar alguna acción adicional si es necesario
        console.log('Usuario cargado correctamente.');
        alert('El usuario se ha cargado correctamente.');
        
        setName('');
        setLastName('');
        setEmail('');
        setPassword('');


        //window.location.reload(); // Recarga la web
      } else {
        // La solicitud no se pudo completar correctamente
        console.error('Error al cargar usuario.');
      }
    } catch (error) {
      // Error en la conexión o al procesar la solicitud
      console.error('Ocurrió un error:', error);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    loadUser();
  };

  return (
    <section className='section-register'>

      <form className='form-register' onSubmit={handleSubmit}>
        <div>
          <h4 className='.h4'>¡Te damos la bienvenida a Samsara Spa!</h4>
          <p>Ingresa tus datos y disfruta de nuestros servicios de relajación.</p>
          <br></br>
        </div>
        <div>
          <label htmlFor="name" >
            Nombre:
          </label>
          <input
          className='input'
            placeholder='Nombre'
            type="text"
            id="name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            
          />
        </div>
        <div>
          <label htmlFor="lastName" >
            Apellido:
          </label>
          <input
          className='input'
            placeholder='Apellido'
            type="text"
            id="lastName"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
            
          />
        </div>
        <div >
          <label htmlFor="email">
            Email:
          </label>
          <input
          className='input'
            placeholder='Email'
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            
          />
        </div>
        <div>
          <label htmlFor="password" >
            Contraseña:
          </label>
          <input className='input'
            placeholder='Contraseña'
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            
          />
        </div>

          <button className="primary-button login size " id='height' type="submit" >
            Registrarse
          </button>


      </form>


      <div className="img-register">
        <img src="https://spa-e3-imagenes.s3.us-east-2.amazonaws.com/friends.jpg" />
      </div>
    </section>
  );
}



export default Register;



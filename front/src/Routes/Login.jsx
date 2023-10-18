import { useContext } from "react";
import { AuthContext } from "../Components/utils/AuthContext";
import { useNavigate } from "react-router-dom";
import React, { useState } from 'react';
import '../Styles/Login.css'

const Login = () => {
  const { dispatch } = useContext(AuthContext);
  const navigate = useNavigate();
  const [userName, setuserName] = useState('');
  const [password, setpassword] = useState('');
  const [tokenReceived, settokenReceived] = useState('');

  

  const handleSubmit = () => {
    loginUser()
      .then((response) => {
        dispatch({ type: "LOGIN", payload: { user: response.user, rol: response.user.rol, accessToken: response.token } });
        navigate("/");
      })
      .catch((error) => {
        console.error("Ocurrió un error al realizar el inicio de sesión:", error);
      });
  };

  const loginUser = async () => {
    try {
      const response = await fetch('http://ec2-3-17-204-179.us-east-2.compute.amazonaws.com:8080/api/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          userName: userName,
          password: password
        })
      });

      if (response.ok) {
        const json = await response.json();
        return json;
      } else {
        alert('Pruebe con un usuario/contraseña válido.');
        throw new Error("La solicitud no se pudo completar correctamente");
      }
    } catch (error) {
      throw new Error("Error en la conexión o al procesar la solicitud: " + error.message);
    }
  };


  return (
    <section className="section-login">
      <div className="form-login">
        <h4 className="h4">¡Te damos la bienvenida!</h4>
        <p className="subtile">Estamos encantados de tenerte aquí. Por favor, completa los campos para acceder a tu cuenta, esto es obligatorio y en caso de no estar registrado deberás registrarte.</p>
        <br></br>
        
        <input className="input-form" type="text" placeholder="Nombre de usuario"
          value={userName}
          onChange={(e) => setuserName(e.target.value)}
        />
        
        <input className="input-form" type="password" placeholder="Contraseña"

          id="password"
          value={password}
          onChange={(e) => setpassword(e.target.value)}
        />
        <div className="details">
          <p>¿Olvidaste tu contraseña?</p>
          <label>
            <input type="checkbox" />
            Recuérdame
          </label>
        </div>

        <button className="primary-button login size" onClick={handleSubmit} >
          Iniciar sesión
        </button>
      </div>

      <div className="img-login">
        <img src="https://spa-e3-imagenes.s3.us-east-2.amazonaws.com/selva.jpg" />
      </div>
    </section>
  );
};


export default Login;


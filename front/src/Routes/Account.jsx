import React from 'react';
import "../Styles/Account.css"
import { useContext } from "react";
import { AuthContext } from "../Components/utils/AuthContext";
import { useNavigate } from "react-router-dom";
import Profile from '../Components/Profile';

const AccountSettings = () => {
  const {user} = useContext(AuthContext);

  const { dispatch } = useContext(AuthContext);
  const navigate = useNavigate();
  const handleSubmit = () => {

    dispatch({ type: "LOGOUT" });
    navigate("/");
    window.location.reload()
  };



  return (
    <div className='body-account'>
      <div className="account-settings">
              <Profile nombre={user.name} apellido={user.lastName}/>
              <h4 className="section-title">Mi perfil</h4>
              <div className="account-info">
                <div className="personal-info">
                  <hr/>
                  <p>
                    <strong>Nombre de usuario:</strong> {user.userName}
                  </p>
                  <hr/>
                  <p>
                    <strong>Nombre:</strong> {user.name}
                  </p>
                  <hr/>
                  <p>
                    <strong>Apellido:</strong> {user.lastName}
                  </p>
                  <hr/>
                  
                  <p>
                    <strong>Email:</strong>{user.email}
                  </p>
                  <hr/>
                  
                </div>
                <div className='buttonLogout'>
                  <button className="logout" onClick={handleSubmit}>
                    Cerrar Sesión
                  </button>
                </div>
        </div>

      </div>
      {/*
      <h2 className="section-title">Reservas</h2>
      <div className="reservation-form">
        <input type="text" placeholder="Experiencia elegida" className="input-field" />
        <input type="date" placeholder="Fecha de viaje" className="input-field" />
        <input type="text" placeholder="Método de pago" className="input-field" />
        <button className="submit-button">Realizar reserva</button>
      </div>
  */}
    </div>
  );
};


export default AccountSettings;

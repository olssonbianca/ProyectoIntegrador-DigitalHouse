import React, { useState } from 'react';
import '../Styles/DatePicker.css';

const MapImage = () => {
  const [showMap, setShowMap] = useState(false);

  const handleClick = () => {
    setShowMap(true);
  };

  const handleClose = () => {
    setShowMap(false);
  };

  return (
    <div
      style={{
        position: 'relative',
        width: '90%',
        height: '330px',
        margin: '10px',
        
      }}
    >
      <img
        src="https://spa-e3-imagenes.s3.us-east-2.amazonaws.com/maps.jpeg"
        alt="Mapa"
        style={{
          width: '100%',
          height: '100%',
          objectFit: 'cover',
          borderRadius: '5px',
        }}
      />
      {showMap && (
        <div
          style={{
            position: 'absolute',
            top: '50%',
            left: '50%',
            transform: 'translate(-50%, -50%)',
            backgroundColor: 'white',
            padding: '20px',
            borderRadius: '4px',
            boxShadow: '0 0 10px rgba(0, 0, 0, 0.2)',
            zIndex: '1',
            width: '340px',
            height: '268px',
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            justifyContent: 'center',
          }}
        >
          <button
            onClick={handleClose}
            style={{
              position: 'absolute',
              top: '10px',
              right: '10px',
              backgroundColor: 'white',
              color: 'green',
              border: 'none',
              borderRadius: '4px',
              padding: '5px 10px',
              cursor: 'pointer',
            }}
          >
            Cerrar
          </button>
          <iframe
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d48802.30110924457!2d-71.34082023891686!3d-40.13908003284578!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x96110e743605d0c7%3A0x71c43ffb1c66bc3a!2sSan%20Martin%20de%20los%20Andes%2C%20Neuqu%C3%A9n!5e0!3m2!1ses!2sar!4v1686437560365!5m2!1ses!2sar"
            width="100%"
            height="100%"
            style={{
              width: '100%',
              height: '100%',
              border: '0',
            }}
            allowFullScreen
            title="Mapa"
          ></iframe>
        </div>
      )}
      {!showMap && (
        <button
          onClick={handleClick}
          style={{
            position: 'absolute',
            top: '50%',
            left: '50%',
            transform: 'translate(-50%, -50%)',
            backgroundColor: 'white',
            color: 'green',
            border: 'none',
            borderRadius: '4px',
            padding: '10px 20px',
            cursor: 'pointer',
          }}
        >
          Ver en el mapa
        </button>
      )}
    </div>
  );
};

export default MapImage;


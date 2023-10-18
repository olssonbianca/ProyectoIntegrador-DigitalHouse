import React, { useState } from 'react';

const AvailabilityCalendar = () => {
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const handleStartDateChange = (e) => {
    setStartDate(e.target.value);
  };

  const handleEndDateChange = (e) => {
    setEndDate(e.target.value);
  };

  const checkAvailability = async () => {
    if (startDate && endDate) {
      setIsLoading(true);

      try {
        // Simulación de una llamada asíncrona para comprobar la disponibilidad
        await new Promise((resolve) => setTimeout(resolve, 2000));

        console.log(`Disponibilidad verificada para el rango de fechas: ${startDate} - ${endDate}`);
      } catch (error) {
        console.error('Error al verificar la disponibilidad:', error);
      } finally {
        setIsLoading(false);
      }
    } else {
      alert('Por favor, seleccione ambas fechas');
    }
  };

  return (
    <div
      style={{
        position: 'relative',
        width: '150px',
        padding: '20px',
        backgroundColor: '#f2f2f2',
        borderRadius: '5px',
      }}
    >
      <h2
        style={{
          marginBottom: '10px',
          fontSize: '18px',
          color: 'green',
        }}
      >
        Disponibilidad de fechas
      </h2>
      <div
        style={{
          display: 'flex',
          flexDirection: 'column',
          gap: '10px',
        }}
      >
        <label
          htmlFor="startDate"
          style={{
            fontSize: '14px',
            color: 'green',
          }}
        >
          Fecha de entrada:
        </label>
        <input
          type="date"
          id="startDate"
          value={startDate}
          onChange={handleStartDateChange}
          style={{
            padding: '6px 8px',
            border: '1px solid #ccc',
            borderRadius: '4px',
          }}
        />
        <label
          htmlFor="endDate"
          style={{
            fontSize: '14px',
            color: 'green',
          }}
        >
          Fecha de salida:
        </label>
        <input
          type="date"
          id="endDate"
          value={endDate}
          onChange={handleEndDateChange}
          style={{
            padding: '6px 8px',
            border: '1px solid #ccc',
            borderRadius: '4px',
          }}
        />
        <button
          className={`availability-button ${isLoading ? 'loading' : ''}`}
          onClick={checkAvailability}
          disabled={isLoading}
          style={{
            padding: '8px 16px',
            backgroundColor: '#4caf50',
            color: 'white',
            border: 'none',
            borderRadius: '4px',
            fontSize: '14px',
            cursor: 'pointer',
            transition: 'background-color 0.3s ease',
          }}
        >
          {isLoading ? 'Verificando...' : 'Ver disponibilidad'}
        </button>
      </div>
    </div>
  );
};

export default AvailabilityCalendar;

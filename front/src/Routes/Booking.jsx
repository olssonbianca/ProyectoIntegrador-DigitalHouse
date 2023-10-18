import React, { useEffect, useState, useContext } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { AuthContext } from '../Components/utils/AuthContext';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { isWithinInterval, parse } from "date-fns";
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Paper from '@mui/material/Paper';
import Container from '@mui/material/Container';
import '../Styles/Booking.css'




const Booking = () => {
  const fechaEntrada = new Date(localStorage.getItem("fechasElegidasComienzo"));
  const fechaSalida = new Date(localStorage.getItem("fechasElegidasFin"));
  const fechaEntradaString = fechaEntrada.toLocaleDateString();
  const fechaSalidaString = fechaSalida.toLocaleDateString();
  const { user } = useContext(AuthContext);

  // Obteniendo el ID de los parámetros de la URL
  const { id } = useParams();
  const navigate = useNavigate();

  // Estado para la fecha de inicio y fin del rango seleccionado
  const [startDate, setStartDate] = useState();
  const [endDate, setEndDate] = useState();

  // Estado para almacenar los detalles de la tarjeta
  const [cardDetails, setCardDetails] = useState({});

  // Función para obtener los detalles de la tarjeta mediante una petición fetch
  const getDetails = async () => {
    try {
      const response = await fetch(`http://ec2-3-17-204-179.us-east-2.compute.amazonaws.com:8080/api/experience/${id}`);
      const json = await response.json();
      setCardDetails(json);
    } catch (error) {
      console.error('Error fetching card details:', error);
    }
  };

  // useEffect para llamar a getDetails() una vez al cargar el componente
  useEffect(() => {
    getDetails();
  }, []);

  // Lista de fechas reservadas
  const reservations = [
    "2023-06-28",
    "2023-06-25"
  ].map((dateStr) => parse(dateStr, "yyyy-MM-dd", new Date()));

  // Función para comprobar si una fecha individual está deshabilitada
  const isDateDisabled = (date) => {
    // Comprueba si la fecha está presente en la lista de reservas
    return reservations.some(
      (reservationDate) => reservationDate.getTime() === date.getTime()
    );
  };

  // Función para comprobar si un rango de fechas está deshabilitado
  const isRangeDisabled = (start, end) => {
    if (start && end) {
      // Comprueba si alguna fecha dentro del rango está presente en la lista de reservas
      return reservations.some((reservationDate) =>
        isWithinInterval(reservationDate, { start, end })
      );
    }
    return false;
  };

  // Manejador de cambio de fecha
  const handleDateChange = (dates) => {
    const [start, end] = dates;
    if (isRangeDisabled(start, end)) {
      // Si el rango seleccionado está deshabilitado, muestra una alerta al usuario y limpia los campos
      alert("Esta experiencia ya está reservada en esas fechas.");
      setStartDate(null);
      setEndDate(null);
      return;
    }

    // Actualiza las variables de estado con las fechas seleccionadas
    setStartDate(start);
    setEndDate(end);
  };

  // Manejador de eventos para modificar la fecha
  const handleModifyDate = () => {
    if (startDate && endDate) {
      // Modificar las fechas en el localStorage
      localStorage.setItem("fechasElegidasComienzo", startDate);
      localStorage.setItem("fechasElegidasFin", endDate);
      window.location.reload()
      // Mostrar mensaje de éxito
      alert("Las fechas de reserva han sido modificadas correctamente.");
    } else {
      // Las fechas de inicio y/o fin no han sido seleccionadas
      alert("Por favor, seleccione las fechas de inicio y fin para modificar la reserva");
    }
  };

  const handleSubmit = () => {
    handleReservation()
  };

  // Manejador de eventos para confirmar la reserva
  const handleReservation = async () => {
    try {
      const response = await fetch('http://ec2-3-17-204-179.us-east-2.compute.amazonaws.com:8080/api/booking', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          entryDate: fechaEntrada,
          departureDate: fechaSalida,
          idUser: user.idUser,
          experienceId: cardDetails.experienceId
        })
      });

      if (response.ok) {
        // Confirmar reserva
        navigate("/confirmation");
        return;
      } else {
        alert('Falló la confirmación de la reserva');
        throw new Error("La solicitud no se pudo completar correctamente");
      }
    } catch (error) {
      throw new Error("Error en la conexión o al procesar la solicitud: " + error.message);
    }
  };

  return (
    <>
      <Container component="main" maxWidth="sm" sx={{ mb: 4 }}>
        <Paper elevation={3} sx={{ my: { xs: 3, md: 6 }, p: { xs: 2, md: 3 } }}>
        <Grid item xs={12} style={{marginBottom:40}} >
          <div className='image-confirm'>
                {cardDetails.experienceImageList && (
                  <img
                    src={cardDetails.experienceImageList[0].image.urlImage}
                    alt='servicio'
                  />
                )}
            </div>
            <h4 className='confirm'>
          Confirma tu reserva
        </h4>
            <h5 className='titleReserva'> {cardDetails.experienceName} </h5> 
            <p> {cardDetails.description}</p>
            <hr></hr>
        </Grid>
        <Grid container spacing={3}>

          <Grid item xs={12} sm={6}>
            <TextField
              id="standard-read-only-input"
              label="Nombre"
              defaultValue={user.name}
              InputProps={{
                readOnly: true,
              }}
              variant="standard" />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              id="standard-read-only-input"
              label="Apellido"
              defaultValue={user.lastName}
              InputProps={{
                readOnly: true,
              }}
              variant="standard"
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              id="standard-read-only-input"
              label="Correo"
              defaultValue={user.email}
              InputProps={{
                readOnly: true,
              }}
              variant="standard"
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              id="standard-read-only-input"
              label="Fecha de entrada y salida"
              defaultValue= {fechaEntradaString + ' - ' + fechaSalidaString }
              InputProps={{
                readOnly: true,
              }}
              variant="standard"
            />
          </Grid>
          <Grid item xs={12}  style={{marginTop:30, marginBottom:30}} >
          <DatePicker
                        className='date'          
                        wrapperClassName="datePicker"
                        selected={startDate}
                        startDate={startDate}
                        endDate={endDate}
                        onChange={handleDateChange}
                        minDate={new Date()}
                        selectsRange
                        isDateBlocked={isDateDisabled}
                        monthsShown={2}
                        dateFormat="dd/MM/yyyy"
                        excludeDates={reservations}
                        placeholderText='Modificar fecha'
                      />
            <button className="modifyButton" onClick={handleModifyDate}>Modificar fecha</button>

           </Grid>
        </Grid>
              <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
                  <button className="primary-button confitmButton " onClick={handleSubmit}>Confirmar reserva</button>
              </Box>
        </Paper>
              </Container>

    </>
  );
};

export default Booking;

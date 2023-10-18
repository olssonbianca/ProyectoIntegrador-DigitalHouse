import React, { useEffect, useState, useContext } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import ThemeContext from '../Components/utils/Context';
import { AuthContext } from '../Components/utils/AuthContext';
import '../Styles/Details.css';
import Maps from '../Components/Maps';
import Facilities from '../Components/Facilities';
import Terms from '../Components/Terms';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import '../Styles/DatePicker.css';
import { isWithinInterval, parse } from "date-fns";

const Detail = () => {
  // Contexto y navegación
  const context = useContext(ThemeContext);
  const navigate = useNavigate();

  // Obteniendo el ID de los parámetros de la URL
  const { id } = useParams();

  // Estado para almacenar los detalles de la tarjeta
  const [cardDetails, setCardDetails] = useState({});

  // Estado para almacenar las reservas ya realizadas
  const [bookings, setBookings] = useState([]);

  // Estado para la fecha de inicio y fin del rango seleccionado
  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);

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

  // Lista de fechas reservadas
  const [reservationsDates, setReservationsDates] = useState([]);

  // ...

  // useEffect para llamar a getDetails() una vez al cargar el componente
  useEffect(() => {
    getDetails();
    getBookings();
  }, []);

  // ...







  // Función para obtener los detalles de la tarjeta mediante una petición fetch
  // Función para obtener los detalles de la tarjeta mediante una petición fetch

  const getBookings = async () => {
    const i = parseInt(id);
    const currentDate = new Date();

    const year = currentDate.getFullYear();
    const month = String(currentDate.getMonth() + 1).padStart(2, '0');
    const day = String(currentDate.getDate()).padStart(2, '0');

    const formattedCurrentDate = `${year}-${month}-${day}`;

    //console.log(formattedCurrentDate);

    const currentDate2 = new Date();
    currentDate2.setDate(currentDate.getDate() + 60);
    const yearf = currentDate2.getFullYear();
    const monthf = String(currentDate2.getMonth() + 1).padStart(2, '0');
    const dayf = String(currentDate2.getDate()).padStart(2, '0');

    const futureDate = `${yearf}-${monthf}-${dayf}`;

    //console.log(futureDate);


    const response = await fetch('http://ec2-3-17-204-179.us-east-2.compute.amazonaws.com:8080/api/booking/invalidDates', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        experienceId: i,
        from: formattedCurrentDate,
        to: futureDate
      })
    });
    const json = await response.json();

    const reservations1 = json.dateRanges;
    //console.log(reservations1, typeof reservations1);
    const reservations = reservations1.map((dateStr) => parse(dateStr, "yyyy-MM-dd", new Date()));

    setReservationsDates(reservations);
  };


  // Función para comprobar si una fecha individual está deshabilitada
  const isDateDisabled = (date) => {
    // Comprueba si la fecha está presente en la lista de reservas
    return reservationsDates.some(
      (reservationDate) => reservationDate.getTime() === date.getTime()
    );
  };

  // Función para comprobar si un rango de fechas está deshabilitado
  const isRangeDisabled = (start, end) => {
    if (start && end) {
      // Comprueba si alguna fecha dentro del rango está presente en la lista de reservas
      return reservationsDates.some((reservationDate) =>
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


  

  // Manejador de eventos para realizar la reserva
  const handleReservation = () => {
    if (startDate && endDate) {
      if (!localStorage.getItem("rol")) {
        navigate('/login');
        return;
      }
      localStorage.setItem("fechasElegidasComienzo", startDate)
      localStorage.setItem("fechasElegidasFin", endDate)
      navigate(`../booking/${id}`);
      return;


    } else {
      // Las fechas de inicio y/o fin no han sido seleccionadas
      alert("Por favor, seleccione las fechas de inicio y fin para realizar la reserva");
    }
  };

  return (
    <>

      <div >

        <div className='images-services'>
          {cardDetails.experienceImageList &&
            cardDetails.experienceImageList.map((experienceImage, index) => (
              <img
                className={`imagen ${experienceImage.enlarged ? 'enlarged' : ''}`}
                src={experienceImage.image.urlImage}
                alt='servicio'
                key={experienceImage.id}
              />
            ))}
        </div>

        <div className='title-service'>
          <div>
            <h1>{cardDetails.experienceName}</h1>
            <h5>{cardDetails.description}</h5>
          </div>
        </div>

        <div className='details-block'>
          <div className='description-block'>
            <div>
              <h4>{cardDetails.experienceName}</h4>
              <p>{cardDetails.description}</p>
            </div>

            <Facilities />
            <Terms />
 
          </div>
          <div className='reserva-block'>
          <Maps />
          <div className='reservas'>
              <h4>¡Ven a visitarnos!</h4>
                <DatePicker className='datepicker'
                  placeholderText='Selecciona una fecha'
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
                  excludeDates={reservationsDates}
                />
                <button className="primary-button reservar " onClick={handleReservation}>Reservar</button>
            </div> 
          </div>
        </div>
      </div>

    </>
  );
};

export default Detail;

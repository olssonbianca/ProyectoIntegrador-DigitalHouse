import { Link } from 'react-router-dom';
import React, { useEffect, useState, useContext, useReducer } from 'react';
import ThemeContext from './utils/Context';
import '../Styles/Cards.css';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import { Button, CardActionArea, CardActions } from '@mui/material';

const ExperienceList = ({ experienceId, experienceName }) => {
  const localValue = JSON.parse(localStorage.getItem('favs'));

  const [favs, dispatch] = useReducer(favsReducer, localValue ?? []);

  const addFav = () => {
    const newFav = { name, username, id };
    if (!favs.find((item) => item.id === newFav.id)) {
      dispatch({ type: 'ADD_FAV', payload: newFav });
    }
  };

  const context = useContext(ThemeContext);
  const theme = context.theme;
  const [experiences, setExperiences] = useState([]);

  useEffect(() => {
    localStorage.setItem('favs', JSON.stringify(favs));
  }, [favs]);

  useEffect(() => {
  
  }, [experienceId]);

  const deleteExperience = async () => {
    try {
      const response = await fetch(`http://ec2-3-17-204-179.us-east-2.compute.amazonaws.com:8080/api/experience/${experienceId}`, {
        method: 'DELETE',
      });

      if (response.ok) {
        // La solicitud se realizó con éxito
        // Aquí puedes realizar alguna acción adicional si es necesario
        console.log(`Experiencia con ID ${experienceId} eliminada correctamente.`)
        alert('La experiencia se ha eliminado correctamente.');
        window.location.reload(); // Recarga la web

        // Actualiza la lista de experiencias si es necesario
        // Por ejemplo, puedes llamar a una función que actualice el estado
        // setExperiences(json) en este caso.
      } else {
        // La solicitud no se pudo completar correctamente
        console.error(`Error al eliminar experiencia con ID ${experienceId}.`);
      }
    } catch (error) {
      // Error en la conexión o al procesar la solicitud
      console.error('Ocurrió un error:', error);
    }
  };

  function favsReducer(state, action) {
    switch (action.type) {
      case 'ADD_FAV':
        return [...state, action.payload];
      default:
        return state;
    }
  }

  return (
    <>
      <Card sx={{ maxWidth: 345 , minWidth: 250}} style={{margin: 10}}>
        <CardActionArea>
        <CardContent>
            <h4>{experienceName}</h4>
        </CardContent>
      </CardActionArea>
      <CardActions>
        <Button size="small" color='secondary'onClick={() => deleteExperience()}>
          Eliminar
        </Button>
      </CardActions>
    </Card>

    </>
  );
};

export default ExperienceList;

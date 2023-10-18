import { Link, NavLink } from 'react-router-dom'
import React, { useEffect, useState } from 'react'
import { useContext, useReducer } from 'react'
import ThemeContext from './utils/Context'
import Grid from '@mui/material/Grid';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';

import '../Styles/Cards.css'



// cuando conecte a la base de datos uso esto: const Category= ({ categoryName }) => {
const Experience = ({ experienceId, experienceName, description, image }) => {


  const localValue = JSON.parse(localStorage.getItem('favs'));

  const [favs, dispatch] = useReducer(favsReducer, localValue ?? []);

  const addFav = () => {
    const newFav = { name, username, id };
    if (!favs.find((item) => item.id === newFav.id)) {
      dispatch({ type: 'ADD_FAV', payload: newFav });
    }
  }

  const context = useContext(ThemeContext);
  const theme = context.theme;

  useEffect(() => {
    localStorage.setItem('favs', JSON.stringify(favs));
  }, [favs]);



  function favsReducer(state, action) {
    switch (action.type) {
      case 'ADD_FAV':
        return [...state, action.payload];
      default:
        return state;
    }
  }

  return (

          <Grid item md={6}>
            <Card sx={{ display: 'flex' }}>
              <CardMedia
                component="img"
                sx={{ width: 240, height: 280, display: { xs: 'none', sm: 'flex' } }}
                image={image}
              />
              <CardContent sx={{ flex: 1 }}>
                <h4>
                  {experienceName}
                </h4>
                <p>
                  {description}
                </p>
                <Link to={`details/${experienceId}`} >
                  <button className='button-card secondary-text-button'>Ver más</button>
                </Link>
              </CardContent>
            </Card>
    </Grid>


  );
};

export default Experience;

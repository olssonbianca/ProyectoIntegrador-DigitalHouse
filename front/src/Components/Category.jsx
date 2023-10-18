import React, { useEffect, useState } from 'react'
import { useContext, useReducer } from 'react'
import ThemeContext from './utils/Context'
import '../Styles/Cards.css'
import { Grid } from '@mui/material';
import Card from '@mui/material/Card';






// cuando conecte a la base de datos uso esto: const Category= ({ categoryName }) => {
  const Category= ({ categoryName,image }) => {

  const localValue = JSON.parse(localStorage.getItem('favs'));
  const [favs, dispatch] = useReducer(favsReducer, localValue ?? []);

  const addFav = () => {
    const newFav = { name, username, id };
    if (!favs.find((item) => item.id === newFav.id)) {
      dispatch({ type: 'ADD_FAV', payload: newFav });
    }
  }

  const context = useContext(ThemeContext);

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
    < >
    <Grid item md={4}>
            <Card    className='category-card' sx={{ display: 'flex' }} style={{ backgroundImage: `url(${image})`}}>
                <h2  style={{color:'#ffffff'}}>{categoryName}</h2>
            </Card>
    </Grid>

    </>
  );
};

export default Category;

import { Link } from 'react-router-dom';
import React, { useEffect, useState, useContext, useReducer } from 'react';
import ThemeContext from './utils/Context';
import '../Styles/Cards.css';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import { Button, CardActionArea, CardActions } from '@mui/material';



const CategoryList = ({ categoryId, categoryName }) => {
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
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    localStorage.setItem('favs', JSON.stringify(favs));
  }, [favs]);

  useEffect(() => {
  
  }, [categoryId]);

  const deleteCategory = async () => {
    try {
      const response = await fetch(`http://ec2-3-17-204-179.us-east-2.compute.amazonaws.com:8080/api/category/${categoryId}`, {
        method: 'DELETE',
      });

      if (response.ok) {
        console.log(`Categoría con ID ${categoryId} eliminada correctamente.`);
        alert('La categoría se ha eliminado correctamente.');
        window.location.reload();
      } else {
        console.error(`Error al eliminar categoría con ID ${categoryId}.`);
      }
    } catch (error) {
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

      <Card sx={{ maxWidth: 345 , minWidth: 250}} style={{margin: 10}}>
        <CardActionArea>
        <CardContent>
            <h4>{categoryName}</h4>
        </CardContent>
      </CardActionArea>
      <CardActions>
        <Button size="small" color='secondary' onClick={() => deleteCategory()}>
          Eliminar
        </Button>
      </CardActions>
    </Card>

  );
};

export default CategoryList;

import { createTheme } from '@mui/material/styles';
import { red } from '@mui/material/colors';

// Create a theme instance.
const theme = createTheme({
  palette: {
    header:{
      main: 'rgba(0, 0, 0, 0.411)',
    },
    primary: {
      main: '#89DB50',
      light:'#ABE086', 
    },
    secondary: {
      main: '#9E65B4',
      light: '#DFB9F2',
    },
    gray:{
        white:'#FEFEFE',
        dark:'#212121',
        beige:'#FAF3E3',
    },
    error: {
      main: red.A400,
    },
  },
});

export default theme;

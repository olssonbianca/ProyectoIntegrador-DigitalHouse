import React, { useContext, useState } from 'react';

import Form from '../Components/Form';
import AddCategory from '../Components/AddCategory';
import ThemeContext from '../Components/utils/Context';
import ExperienceList from '../Components/ExperienceList';
import CategoryList from '../Components/CategoryList';
import '../Styles/Dashboard.css';
import AdminUser from '../Components/AdminUser';


//Mui material
import { styled, createTheme, ThemeProvider } from '@mui/material/styles';
import MuiDrawer from '@mui/material/Drawer';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Collapse from '@mui/material/Collapse';
import InboxIcon from '@mui/icons-material/MoveToInbox';
import ExpandLess from '@mui/icons-material/ExpandLess';
import ExpandMore from '@mui/icons-material/ExpandMore';
import StarBorder from '@mui/icons-material/StarBorder';



const drawerWidth = 300;


const Drawer = styled(MuiDrawer, { shouldForwardProp: (prop) => prop !== 'open' })(
  ({ theme, open }) => ({
    '& .MuiDrawer-paper': {
      position: 'relative',
      whiteSpace: 'nowrap',
      width: drawerWidth,
      transition: theme.transitions.create('width', {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.enteringScreen,
      }),
      boxSizing: 'border-box',
      ...(!open && {
        overflowX: 'hidden',
        transition: theme.transitions.create('width', {
          easing: theme.transitions.easing.sharp,
          duration: theme.transitions.duration.leavingScreen,
        }),
        width: theme.spacing(7),
        [theme.breakpoints.up('sm')]: {
          width: theme.spacing(9),
        },
      }),
    },
  }),
);
const defaultTheme = createTheme();





const Dashboard = () => {
  const [activeMenu, setActiveMenu] = useState('addCategory');
  const context = useContext(ThemeContext);
  const [open, setOpen] = React.useState(true);
  const toggleDrawer = () => {
    setOpen(!open);
  };

  const handleClick = () => {
    setOpen(!open);
  };

  const renderComponent = () => {
    switch (activeMenu) {
      case 'addCategory':
        return <AddCategory />;
      case 'categoryList':
        return (
          <>
            {context.categories.map((category) => (
              <CategoryList
                key={category.categoryId}
                categoryId={category.categoryId}
                categoryName={category.categoryName}
              />
            ))}
          </>
        );
      case 'addExperience':
        return <Form />;
      case 'experienceList':
        return (
          <>
            {context.experiences.map((experience) => (
              <ExperienceList
                key={experience.experienceId}
                experienceId={experience.experienceId}
                experienceName={experience.experienceName}
              />
            ))}
          </>
        );
      case 'adminUser':
        return <AdminUser />;
      default:
        return null;
    }
  };

  return (
    <ThemeProvider theme={defaultTheme}>
      <Box sx={{ display: 'flex' }}>
        <Drawer className='drawer' variant="permanent" open={open}>
          <Toolbar
            sx={{
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'flex-end',
              px: [1],
            }}
          >
            <IconButton onClick={toggleDrawer}>
              <ChevronLeftIcon />
            </IconButton>
          </Toolbar>
          <Divider />
          <List
            sx={{ width: '100%', maxWidth: 300, bgcolor: 'background.paper' }}
            component="nav"
            aria-labelledby="nested-list-subheader"
          >
            <ListItemButton onClick={handleClick}>
              <ListItemIcon>
                <InboxIcon />
              </ListItemIcon>
              <ListItemText primary="Categoría" />
              {open ? <ExpandLess /> : <ExpandMore />}
            </ListItemButton>
            <Collapse in={open} timeout="auto" unmountOnExit>
              <List component="div" disablePadding>

                <ListItemButton sx={{ pl: 4 }}>
                  <ListItemIcon>
                    <StarBorder />
                  </ListItemIcon>
                  <ListItemText><li onClick={() => setActiveMenu('addCategory')}>
                    Agregar categoría </li> </ListItemText>
                </ListItemButton>

                <ListItemButton sx={{ pl: 4 }}>
                  <ListItemIcon>
                    <StarBorder />
                  </ListItemIcon>
                  <ListItemText><li onClick={() => setActiveMenu('categoryList')}>
                    Eliminar categoría </li></ListItemText>
                </ListItemButton>
              </List>
            </Collapse>


            <ListItemButton onClick={handleClick}>
              <ListItemIcon>
                <InboxIcon />
              </ListItemIcon>
              <ListItemText primary="Experiencias" />
              {open ? <ExpandLess /> : <ExpandMore />}
            </ListItemButton>
            <Collapse in={open} timeout="auto" unmountOnExit>
              <List component="div" disablePadding>

                <ListItemButton sx={{ pl: 4 }}>
                  <ListItemIcon>
                    <StarBorder />
                  </ListItemIcon>
                  <ListItemText> <li onClick={() => setActiveMenu('addExperience')}>
                    Agregar experiencia</li></ListItemText>
                </ListItemButton>

                <ListItemButton sx={{ pl: 4 }}>
                  <ListItemIcon>
                    <StarBorder />
                  </ListItemIcon>
                  <ListItemText><li onClick={() => setActiveMenu('experienceList')}>
                    Eliminar experiencia </li></ListItemText>
                </ListItemButton>
              </List>
            </Collapse>


            <ListItemButton onClick={handleClick}>
              <ListItemIcon>
                <InboxIcon />
              </ListItemIcon>
              <ListItemText primary="Administradores" />
              {open ? <ExpandLess /> : <ExpandMore />}
            </ListItemButton>
            <Collapse in={open} timeout="auto" unmountOnExit>
              <List component="div" disablePadding>

                <ListItemButton sx={{ pl: 4 }}>
                  <ListItemIcon>
                    <StarBorder />
                  </ListItemIcon>
                  <ListItemText><li onClick={() => setActiveMenu('adminUser')}>
                    Crear administrador</li></ListItemText>
                </ListItemButton>
              </List>
            </Collapse>

          </List>
        </Drawer>
        <Box
        style={{display:'flexGrow'}}
          component="main"
          sx={{
            backgroundColor: (theme) =>
              theme.palette.mode === 'light'
                ? theme.palette.grey[100]
                : theme.palette.grey[900],
                flexGrow: 1,
            height: '100vh',
            overflow: 'auto',

          }}
        >
          <div className='body-dash'>{renderComponent()}</div>

        </Box>
      </Box>
    </ThemeProvider>
  );

};

export default Dashboard;

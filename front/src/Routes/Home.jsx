import '../Styles/Home.css'
import React from 'react'
import { useContext,useState } from 'react'
import ThemeContext from '../Components/utils/Context'
import Category from '../Components/Category'
import Experience from '../Components/Experience'
import Search from '../Components/Search'
import { Link } from 'react-router-dom'
import Grid from '@mui/material/Grid';


//Este componente debera ser estilado como "dark" o "light" dependiendo del theme del Context

const Home = () => {

  const context = useContext(ThemeContext);


  return (
    <>
   

      <main className="main">
          <div id="title">
            <h1>Un oasis de tranquilidad, aventura y relajación</h1>
            <h4>Descubre nuestras experiencias disponibles</h4>
          </div>
          <div>
            <Search/>
          </div>
      </main>
      <section>
        <div className="category">
          <h1>Santuario de bienestar</h1>
          <h4 className="size">Spa y rituales en contacto con la naturaleza</h4>
        </div>
      <div className='category-section'>
        <Grid container justifyContent="space-evenly" alignItems="center">
            {/* Aqui renderiza las cards cuando conecte a la base de datos */
             context.categories.map((category) => (
              <Link className='decoration' to={`catdetails/${category.categoryId}`} >
                  <Category categoryName={category.categoryName} image={category.image.urlImage}/>
              </Link>
              ))
            }
        </Grid> 
      </div>


        </section>

        <section className="categories">
          <div className="category">
          <h1>Nuestros servicios</h1>
          <h4 className="service-size">Conoce nuestros servicios de relajación y aventura para disfrutar de la naturaleza</h4>
          </div>
          <div className='service-section' >
          <Grid container spacing={4}>
              {context.experiences.map((experience) => (
                <Experience
                  key={experience.experienceId}
                  experienceId={experience.experienceId}
                  experienceName={experience.experienceName}
                  description={experience.description}
                  image={experience.experienceImageList[0]?.image?.urlImage} // Accessing the 'urlImage' property
                />
              ))}
             </Grid> 
            </div>
        </section>
    
    </>
  )
}

export default Home
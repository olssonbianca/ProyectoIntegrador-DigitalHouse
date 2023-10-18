
import React from 'react'
import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { useContext } from 'react'
import ThemeContext from '../Components/utils/Context'
import Experience2 from '../Components/Experience2'
import Grid from '@mui/material/Grid';
import Category from '../Components/Category'
import Search from '../Components/Search'
import { Link } from 'react-router-dom'


const CatDetail = () => {
 

  
  const [catdetails, setDetails] = useState([])
  let params = useParams();
  let id = params.id;

  const getcatDetails = async () => {
    fetch(`http://ec2-3-17-204-179.us-east-2.compute.amazonaws.com:8080/api/experience/category/${id}`)
      .then(res => res.json())
      .then(json => setDetails(json))
  };
  useEffect(() => {
    getcatDetails();
  }, []);


  const context = useContext(ThemeContext);
  const theme = context.theme

  return (
    <div>

      <main className="main">

          <div id="title">
            <h1>Un oasis de tranquilidad, aventura y relajación</h1>
            <h4>Descubre nuestras experiencias disponibles</h4>
          </div>
          <div>
            <Search/>
          </div>

      </main>

        <section className="categories">
          <div className="category">
        
          <h4 className="service-size">Conoce nuestros servicios para disfrutar de la naturaleza</h4>
          </div>

          <div className='service-section' style={{ background: theme.background, color: theme.font }}>
            <Grid container spacing={4}>
            {/* Aqui renderiza las cards cuando conecte a la base de datos */
              catdetails.map((experience) => (
                
                <Experience2 experienceId={experience.experienceId} experienceName={experience.experienceName} description={experience.description} image={experience.experienceImageList[0].image.urlImage}/>
                
            ))
            }
            </Grid>
          </div>
        </section>



    </div>
  )
}

export default CatDetail;






import React, { useContext, useState,useEffect } from 'react';
import ThemeContext from '../Components/utils/Context';

function Formulario() {
  const [experienceName, setExperienceName] = useState('');
  const [categoryId, setCategoryId] = useState(0);
  const [description, setDescription] = useState('');
  const [images, setImages] = useState([]);
  const [imageIds, setImageIds] = useState([]);
  const [loading, setLoading] = useState(false);
  const context = useContext(ThemeContext);

  const handleImageChange = (e) => {
    const files = Array.from(e.target.files);
    setImages(files);
  };

  const uploadImages = async () => {
    try {
      const uploadPromises = images.map(async (file) => {
        const formData = new FormData();
        formData.append('imagen', file);

        const response = await fetch('http://ec2-3-17-204-179.us-east-2.compute.amazonaws.com:8080/api/v1/image/upload/bulk', {
          method: 'POST',
          body: formData,
        });

        if (response.ok) {
          const data = await response.json();
          const firstImage = data[0];

          return firstImage.imageId;

        } else {
          console.error('Error al cargar la imagen.');
          return null;
        }
      });

      const uploadedImageIds = await Promise.all(uploadPromises);
      setImageIds(uploadedImageIds.filter((id) => id !== null));
      console.log('Imágenes cargadas correctamente.');
      console.log(uploadedImageIds);
    } catch (error) {
      console.error('Ocurrió un error:', error);
    }
  };

  const loadExperience = async () => {
    try {
      const response = await fetch('http://ec2-3-17-204-179.us-east-2.compute.amazonaws.com:8080/api/experience', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          experienceName: experienceName,
          categoryId: categoryId,
          description: description,
          imagesToSaved: imageIds,
          characteristicListToSaved: [
            1,
            2,
            3,
            4,
            5
        ]}),
      });

      if (response.ok) {
        console.log(`Experiencia con ID ${experienceName} cargada correctamente.`);
        window.location.reload();
      } else {
        console.error(`Error al cargar experiencia.`);
      }
    } catch (error) {
      console.error('Ocurrió un error:', error);
    }
  };

  useEffect(() => {
    if (imageIds.length > 0) {
      loadExperience();
      setLoading(false);
    }
  }, [imageIds]);

  const handleSubmit = (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      uploadImages();
    } catch (error) {
      console.error('Ocurrió un error:', error);
    }
  };

  return (
    <div className='container'>
      {loading ? (
        <div className="loading-screen">
          <a><img  src="https://i.ibb.co/6H3kfjz/148033-loading-variation.gif" /></a>
          <h4>Cargando Experiencia...</h4>
        </div>
      ) : (
        <form className='form' onSubmit={handleSubmit}>
          <h4>Experiencias</h4>
          <div className='labelFlex'>
            <label htmlFor="experienceName">
              Nombre
            </label>
            <input
              placeholder='Experiencia'
              className='input'
              type="text"
              id="experienceName"
              value={experienceName}
              onChange={(e) => setExperienceName(e.target.value)}
            />
          </div>
          <div className='labelFlex'>
  <label htmlFor="categoryId">
    Categoría
  </label>
  <select
    className='input'
    id="categoryId"
    value={categoryId}
    onChange={(e) => setCategoryId(parseInt(e.target.value))}
  >
    <option value="">Seleccione una categoría</option>
    {context.categories.map((category) => (
      <option key={category.categoryId} value={category.categoryId}>
        {category.categoryName}
      </option>
    ))}
  </select>
</div>


          <div className='labelFlex' >
            <label htmlFor="description" >
              Descripción
            </label>
            <textarea
              placeholder='Descripción'
              className='input'
              id="description"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
            />
          </div>
          <div className="labelFlex">
            <label htmlFor="image">Selecciona las imagenes</label>
            <input
              className="input"
              type="file"
              id="image"
              accept="image/*"
              multiple
              onChange={handleImageChange}
            />
          </div>
          <button className="primary-button login size" id="height1" type="submit">
            Crear experiencia
          </button>
        </form>
      )}
    </div>
  );
}

export default Formulario;

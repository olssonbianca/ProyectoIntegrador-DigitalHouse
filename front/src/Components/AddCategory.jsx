import React, { useState, useEffect,useContext } from 'react';
import { AuthContext } from "../Components/utils/AuthContext";
import '../Styles/AddCategory.css'; 


function AddCategory() {
  const [categoryName, setCategoryName] = useState('');
  const [image, setImage] = useState(null);
  const [imageId, setImageId] = useState();
  const [loading, setLoading] = useState(false);
  const { token } = useContext(AuthContext);
  const handleImageChange = (e) => {
    const file = e.target.files[0];
    setImage(file);
  };

  const uploadImage = async () => {
    try {
      const formData = new FormData();
      formData.append('imagen', image);

      const response = await fetch('http://ec2-3-17-204-179.us-east-2.compute.amazonaws.com:8080/api/v1/image/upload/bulk', {
        method: 'POST',
      body: formData,
      
    });

      if (response.ok) {
        const data = await response.json();
        const firstImage = data[0];
        setImageId(firstImage.imageId);
        console.log('Imagen cargada correctamente.');
        console.log(firstImage.imageId);
      } else {
        console.error('Error al cargar la imagen.');
      }
    } catch (error) {
      console.error('Ocurrió un error:', error);
    }
  };

  const addCategory = async () => {
    try {
      await new Promise((resolve) => setTimeout(resolve, 1000));

      const response = await fetch('http://ec2-3-17-204-179.us-east-2.compute.amazonaws.com:8080/api/category', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          
        },
        body: JSON.stringify({
          categoryName: categoryName,
          imageId: imageId,
        }),
      });

      if (response.ok) {
        console.log(`Categoría con nombre ${categoryName} cargada correctamente.`);
        window.location.reload();
      } else {
        console.error('Error al cargar la categoría.');
        console.error(imageId);
      }
    } catch (error) {
      console.error('Ocurrió un error:', error);
    }
  };

  useEffect(() => {
    if (imageId) {
      addCategory();
      setLoading(false);
    }
  }, [imageId]);

  const handleSubmit = (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      uploadImage();
    } catch (error) {
      console.error('Ocurrió un error:', error);
    }
  };

  return (
    <div className='container'>
      {loading ? (
        <div className="loading-screen">
          <a><img  src="https://i.ibb.co/6H3kfjz/148033-loading-variation.gif" /></a>
          <h4>Cargando Categoría...</h4>
        </div>
      ) : (
        <form className="form" onSubmit={handleSubmit}>
          <h4>Categorias</h4>
          <br></br>
          <div className="labelFlex">
            <label htmlFor="categoryName">Nombre</label>
            <input
              placeholder="Categoría"
              className="input"
              type="text"
              id="categoryName"
              value={categoryName}
              onChange={(e) => setCategoryName(e.target.value)}
            />
          </div>

          <div className="labelFlex">
            <label htmlFor="image">Selecciona una imagen</label>
            <input
              className="input"
              type="file"
              id="image"
              accept="image/*"
              onChange={handleImageChange}
            />
          </div>

          <button className="primary-button login size" id="height1" type="submit">
            Crear categoria
          </button>
        </form>
      )}
    </div>
  );
}

export default AddCategory;

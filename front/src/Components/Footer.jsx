import "../Styles/Footer.css"
import { Link } from "react-router-dom"

const Footer = () => {
  return (
    <footer className="footer">
      <div className="info">
        <Link to={"/"}><img src="https://spa-e3-imagenes.s3.us-east-2.amazonaws.com/logo1.png" alt="logo1" border="0"/></Link>
        <div className="redes">
        <a><img src="https://spa-e3-imagenes.s3.us-east-2.amazonaws.com/spotify.png" alt="spotify" border="0"/></a>
          <a><img src="https://spa-e3-imagenes.s3.us-east-2.amazonaws.com/instagram.png" alt="instagram" border="0"/></a>
          <a><img src="https://spa-e3-imagenes.s3.us-east-2.amazonaws.com/whatsapp.png" alt="whatsapp" border="0"/></a>
          <a><img src="https://spa-e3-imagenes.s3.us-east-2.amazonaws.com/facebook.png" alt="instagram" border="0"/></a>
      </div>
      <br></br>
      <div className="copyright">
          <hr></hr>
          <p className="text-body">©2023 Samsara Spa</p>
      </div>
      </div>
    </footer>
  )
}

export default Footer
import React from 'react';
import '../Styles/Facilities.css';

const SpaFacilities = () => {
  const facilities = [
    { name: 'Wi-Fi', description: 'Conexión gratuita a internet' },
    { name: 'Hidromasaje', description: 'Piscina de hidromasaje' },
    { name: 'Sauna', description: 'Desayuno buffet gratuito' },
    { name: 'Masajes', description: 'Variedad de masajes relajantes' },
    { name: 'Desayuno incluido', description: 'Sauna seco disponible' },
  ];

  return (
    <div >
      <table>
        <thead>
          <tr>
           <h4> ¿Qué ofrece esta experiencia?</h4>
          </tr>
        </thead>
        <tbody className='list-block'>
          {facilities.map((facility, index) => (
            <tr key={index}>
              <td className='list' ><img className="check" src="https://spa-e3-imagenes.s3.us-east-2.amazonaws.com/Check.svg" alt="" /><h6>{facility.name}</h6> </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default SpaFacilities;

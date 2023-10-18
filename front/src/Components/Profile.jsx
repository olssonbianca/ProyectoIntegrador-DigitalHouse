import React, { useEffect, useRef } from "react";

const Profile = ({ nombre, apellido }) => {
  
  const canvasRef = useRef(null);

  useEffect(() => {
    const canvas = canvasRef.current;
    const context = canvas.getContext("2d");

    // Dibujar un círculo
    const centerX = canvas.width / 2;
    const centerY = canvas.height / 2;
    const radius = 28;

    context.beginPath();
    context.arc(centerX, centerY, radius, 0, 2 * Math.PI, false);
    context.fillStyle = "#FEFEFE";
    context.fill();
    context.lineWidth = 2;
    context.strokeStyle = "#ABE086";
    context.stroke();


    // Escribir la inicial del nombre y apellido dentro del círculo
    const initials = nombre.charAt(0) + apellido.charAt(0);
    context.font = "bold 18px Arial";
    context.fillStyle = "#212121";
    context.textAlign = "center";
    context.textBaseline = "middle";
    context.fillText(initials, centerX, centerY);
  }, [nombre, apellido]);

  return <canvas ref={canvasRef} width={60} height={60} ></canvas>;
};

export default Profile;

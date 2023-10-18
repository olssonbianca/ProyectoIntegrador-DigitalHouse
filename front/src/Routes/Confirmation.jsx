import React from 'react';
import { Dialog, DialogTitle, DialogContent, DialogActions, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const Confirmation = () => {
  const navigate = useNavigate();

  const handleCloseConfirmation = () => {
    navigate("/");
  };

  return (
    <Dialog open={true} onClose={handleCloseConfirmation} PaperProps={{ style: { borderRadius: '8px' } }}>
      <DialogTitle style={{ backgroundColor: 'green', color: 'white', display: 'flex', alignItems: 'center', borderRadius: '8px 8px 0 0' }}>
        <div style={{ display: 'flex', alignItems: 'center', margin: 'auto' }}>
          <img src="http://spa-e3-imagenes.s3.amazonaws.com/17687cc9-4b56-4394-a886-a87f8d9a47e6.png" alt="Check" style={{ marginRight: '8px' }} />
        </div>
      </DialogTitle>
      <DialogContent style={{ padding: '16px' }}>
        <h4>Muchas Gracias</h4>
        <p style={{ fontSize: '16px', marginBottom: '16px' }}>La reserva ha sido creada correctamente.</p>
      </DialogContent>
      <DialogActions style={{ justifyContent: 'center', marginBottom: '16px' }}>
        <Button onClick={handleCloseConfirmation} style={{ color: 'green' }}>Cerrar</Button>
      </DialogActions>
    </Dialog>
  );
};

export default Confirmation;

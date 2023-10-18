package com.finalprojectc7t3.backend.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralException {
    @ExceptionHandler({DontFindException.class})
    public ResponseEntity<String> procesoExcepcionBadRequest(DontFindException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler({CreateResourceException.class})
    public ResponseEntity<String> procesoExcepcionBadRequest(CreateResourceException e) {
        return ResponseEntity.status(HttpStatus.FOUND).body(e.getMessage());
    }
}

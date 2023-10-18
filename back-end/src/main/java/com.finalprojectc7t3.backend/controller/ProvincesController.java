package com.finalprojectc7t3.backend.controller;


import com.finalprojectc7t3.backend.dto.ProvincesDTO;
import com.finalprojectc7t3.backend.excepciones.DontFindException;
import com.finalprojectc7t3.backend.service.impl.ProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/provinces")

public class ProvincesController {

    private ProvincesService provincesService;

    @Autowired
    public ProvincesController(ProvincesService service){
        this.provincesService = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProvincesDTO provincesDTO) throws DontFindException {
        return ResponseEntity.status(HttpStatus.CREATED).body(provincesService.create(provincesDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        provincesService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(provincesService.findAll(),null, HttpStatus.OK);
    }



}

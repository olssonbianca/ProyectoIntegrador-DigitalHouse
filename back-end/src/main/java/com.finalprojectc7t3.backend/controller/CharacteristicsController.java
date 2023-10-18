package com.finalprojectc7t3.backend.controller;


import com.finalprojectc7t3.backend.dto.CharacteristicDTO;
import com.finalprojectc7t3.backend.excepciones.DontFindException;
import com.finalprojectc7t3.backend.service.impl.CharacteristicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/characteristics")

public class CharacteristicsController {

    private CharacteristicsService characteristicsService;

    @Autowired
    public CharacteristicsController(CharacteristicsService service){
        this.characteristicsService = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CharacteristicDTO characteristicDTO) throws DontFindException {
        return ResponseEntity.status(HttpStatus.CREATED).body(characteristicsService.create(characteristicDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        characteristicsService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(characteristicsService.findAll(),null, HttpStatus.OK);
    }



}

package com.finalprojectc7t3.backend.controller;


import com.finalprojectc7t3.backend.dto.CampusDTO;
import com.finalprojectc7t3.backend.excepciones.DontFindException;
import com.finalprojectc7t3.backend.service.impl.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/campus")

public class CampusController {

    private final CampusService campusService;

    @Autowired
    public CampusController(CampusService service){
        this.campusService = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CampusDTO campusDTO) throws DontFindException {
        return ResponseEntity.status(HttpStatus.CREATED).body(campusService.create(campusDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        campusService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(campusService.findAll(),null, HttpStatus.OK);
    }



}

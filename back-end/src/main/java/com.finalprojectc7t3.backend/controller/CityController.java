package com.finalprojectc7t3.backend.controller;

import com.finalprojectc7t3.backend.dto.CityDTO;
import com.finalprojectc7t3.backend.service.impl.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;



@RestController
@RequestMapping("api/city")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:80", "http://localhost", "http://localhost:5173"})

public class CityController {
    private final CityService service;

    @Autowired
    public CityController(CityService service){
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CityDTO cityDTO)  {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(cityDTO));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CityDTO cityDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(cityDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(service.findAll(),null, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

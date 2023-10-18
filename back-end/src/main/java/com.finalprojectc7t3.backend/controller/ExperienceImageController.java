package com.finalprojectc7t3.backend.controller;

import com.finalprojectc7t3.backend.dto.ExperienceImageDTO;
import com.finalprojectc7t3.backend.excepciones.DontFindException;
import com.finalprojectc7t3.backend.service.IExperienceImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/v1/experienceImage")
//@CrossOrigin(origins = {"http://bucket-front-e3-c7.s3-website.us-east-2.amazonaws.com:4200", "http://bucket-front-e3-c7.s3-website.us-east-2.amazonaws.com:80", "http://bucket-front-e3-c7.s3-website.us-east-2.amazonaws.com", "http://bucket-front-e3-c7.s3-website.us-east-2.amazonaws.com:5173"})
public class ExperienceImageController {

    private final IExperienceImageService service;

    @Autowired
    public ExperienceImageController(IExperienceImageService service){
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody ExperienceImageDTO experienceImageDTO) throws DontFindException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(experienceImageDTO));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ExperienceImageDTO experienceImageDTO) throws DontFindException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(experienceImageDTO));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

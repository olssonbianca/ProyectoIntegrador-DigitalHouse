package com.finalprojectc7t3.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalprojectc7t3.backend.dto.ExperienceDTO;
import com.finalprojectc7t3.backend.service.impl.ExperienceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/experience")
public class ExperienceController {
    private ExperienceService experienceService;

    @Autowired
    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }
    @Autowired
    private ObjectMapper mapper;

    private static final Logger LOGGER = Logger.getLogger(ExperienceService.class);

    // Endpoint to create a new experience
    @PostMapping
    public ResponseEntity<?> createExperience(@RequestBody ExperienceDTO experienceCreateDTO) {
        return ResponseEntity.ok(experienceService.createExperience(experienceCreateDTO));
    }

    // Endpoint to list an experience by id
    @GetMapping("/{id}")
    public ResponseEntity<ExperienceDTO> getExperienceById(@PathVariable Integer id) {
        LOGGER.info("... ... From ExperienceController class, getExperienceById method is running ... ...");
        ExperienceDTO experienceDTO = mapper.convertValue(
                experienceService.getExperienceById(id), ExperienceDTO.class);
        LOGGER.info("Controller: Experience id: " + id + " has been found.");
        return new ResponseEntity<>(experienceDTO, HttpStatus.OK);
    }

    @GetMapping("/list")
    public Set<ExperienceDTO> getAllExperiences() {
        LOGGER.info("### From ExperienceController class, getAllExperiences method is running ###");
        return experienceService.getAllExperiences();
    }

    @PutMapping
    public ResponseEntity<?> updateAnExperience(@RequestBody ExperienceDTO experienceCreateDTO) {
        return ResponseEntity.ok(experienceService.updateExperience(experienceCreateDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteAnExperience(@PathVariable Integer id) {

        experienceService.deleteExperienceById(id);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getExperienceByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(experienceService.getExperienceByCategory(categoryId));
    }

    @GetMapping("/random")
    public ResponseEntity<?> getExperienceByRandom() {
        return ResponseEntity.ok(experienceService.getExperienceByRandom());
    }
}

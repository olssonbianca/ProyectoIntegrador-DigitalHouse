package com.finalprojectc7t3.backend.service;

import com.finalprojectc7t3.backend.dto.ExperienceCreateDTO;
import com.finalprojectc7t3.backend.dto.ExperienceDTO;

import java.util.List;
import java.util.Set;

public interface IExperienceService {
    ExperienceCreateDTO createExperience (ExperienceDTO experienceCreateDTO);
    ExperienceDTO getExperienceById (Integer id);
    Set<ExperienceDTO> getAllExperiences();
    ExperienceDTO updateExperience (ExperienceDTO experienceCreateDTO);
    void deleteExperienceById (Integer id);
    List<ExperienceDTO> getExperienceByCategory(Long categoryId);
    List<ExperienceDTO> getExperienceByRandom();
}

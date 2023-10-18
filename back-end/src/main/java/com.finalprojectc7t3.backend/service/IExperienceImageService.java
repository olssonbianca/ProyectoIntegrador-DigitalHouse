package com.finalprojectc7t3.backend.service;


import com.finalprojectc7t3.backend.dto.ExperienceImageDTO;
import com.finalprojectc7t3.backend.excepciones.DontFindException;



import java.util.List;


public interface IExperienceImageService {
    ExperienceImageDTO create(ExperienceImageDTO experienceImageDTO) throws DontFindException;

    ExperienceImageDTO update(ExperienceImageDTO imexperienceImageDTOageDTO) throws DontFindException;

    List<ExperienceImageDTO> findAll();

    void delete(Integer id);

    ExperienceImageDTO findByUnique(Long experienceId, Integer idImage);

}

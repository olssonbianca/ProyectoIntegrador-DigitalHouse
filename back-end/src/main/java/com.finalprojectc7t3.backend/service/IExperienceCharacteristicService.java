package com.finalprojectc7t3.backend.service;


import com.finalprojectc7t3.backend.dto.ExperienceCharacteristicDTO;
import com.finalprojectc7t3.backend.excepciones.DontFindException;

import java.util.List;

public interface IExperienceCharacteristicService {

    ExperienceCharacteristicDTO create(ExperienceCharacteristicDTO experienceCharacteristicDTO) throws DontFindException;

    ExperienceCharacteristicDTO update(ExperienceCharacteristicDTO experienceCharacteristicDTO) throws DontFindException;

    List<ExperienceCharacteristicDTO> findAll();

    void delete(Integer id);


}

package com.finalprojectc7t3.backend.service;


import com.finalprojectc7t3.backend.dto.ExperienceCampusDTO;
import com.finalprojectc7t3.backend.excepciones.DontFindException;

import java.util.List;

public interface IExperienceCampusService {

    ExperienceCampusDTO create(ExperienceCampusDTO experienceCampusDTO) throws DontFindException;

    ExperienceCampusDTO update(ExperienceCampusDTO experienceCampusDTO) throws DontFindException;

    List<ExperienceCampusDTO> findAll();

    void delete(Integer id);


}

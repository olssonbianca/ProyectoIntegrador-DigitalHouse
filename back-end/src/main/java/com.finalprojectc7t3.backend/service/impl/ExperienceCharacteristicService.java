package com.finalprojectc7t3.backend.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalprojectc7t3.backend.dto.ExperienceCharacteristicDTO;
import com.finalprojectc7t3.backend.entity.ExperienceCharacteristics;
import com.finalprojectc7t3.backend.repository.IExperienceCharacteristicRepository;
import com.finalprojectc7t3.backend.service.IExperienceCharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExperienceCharacteristicService implements IExperienceCharacteristicService {

    private final IExperienceCharacteristicRepository repository;
    private final ObjectMapper mapper;

    @Autowired
    public ExperienceCharacteristicService(IExperienceCharacteristicRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public ExperienceCharacteristicDTO create(ExperienceCharacteristicDTO experienceCharacteristicDTO) {
        return mapper.convertValue(repository.save(mapper.convertValue(experienceCharacteristicDTO, ExperienceCharacteristics.class)), ExperienceCharacteristicDTO.class);
    }

     @Override
    public List<ExperienceCharacteristicDTO> findAll() {
        return repository.findAll().stream().map(i -> mapper.convertValue(i, ExperienceCharacteristicDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }


    @Override
    public ExperienceCharacteristicDTO update(ExperienceCharacteristicDTO experienceCharacteristicDTO) {
        return create(experienceCharacteristicDTO);
    }

}

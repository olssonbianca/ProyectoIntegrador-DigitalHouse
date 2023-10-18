package com.finalprojectc7t3.backend.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalprojectc7t3.backend.dto.ExperienceCampusDTO;
import com.finalprojectc7t3.backend.entity.ExperienceCampus;
import com.finalprojectc7t3.backend.repository.IExperienceCampusRepository;
import com.finalprojectc7t3.backend.service.IExperienceCampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExperienceCampusService implements IExperienceCampusService {

    private final IExperienceCampusRepository repository;
    private final ObjectMapper mapper;

    @Autowired
    public ExperienceCampusService(IExperienceCampusRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public ExperienceCampusDTO create(ExperienceCampusDTO experienceCampusDTO) {
        return mapper.convertValue(repository.save(mapper.convertValue(experienceCampusDTO, ExperienceCampus.class)), ExperienceCampusDTO.class);
    }

     @Override
    public List<ExperienceCampusDTO> findAll() {
        return repository.findAll().stream().map(i -> mapper.convertValue(i, ExperienceCampusDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }


    @Override
    public ExperienceCampusDTO update(ExperienceCampusDTO experienceCampusDTO) {
        return create(experienceCampusDTO);
    }

}

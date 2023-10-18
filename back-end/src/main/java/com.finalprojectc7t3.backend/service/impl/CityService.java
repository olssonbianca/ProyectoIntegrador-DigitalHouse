package com.finalprojectc7t3.backend.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalprojectc7t3.backend.dto.CityDTO;
import com.finalprojectc7t3.backend.entity.City;
import com.finalprojectc7t3.backend.repository.ICityRepository;
import com.finalprojectc7t3.backend.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService implements ICityService {

    private final ICityRepository repository;
   private final ObjectMapper mapper;

    @Autowired
    public CityService(ICityRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public CityDTO create(CityDTO cityDTO) {

        return mapper.convertValue(repository.save(mapper.convertValue(cityDTO, City.class)), CityDTO.class);
    }

    @Override
    public List<CityDTO> findAll() {
        return repository.findAll().stream().map(i -> mapper.convertValue(i, CityDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public CityDTO findById(Integer id) {
        return null;
    }

    @Override
    public CityDTO update(CityDTO cityDTO) {
        return create(cityDTO);
    }


}

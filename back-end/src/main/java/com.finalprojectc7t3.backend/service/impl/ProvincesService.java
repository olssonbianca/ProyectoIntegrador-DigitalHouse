package com.finalprojectc7t3.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalprojectc7t3.backend.dto.CharacteristicDTO;
import com.finalprojectc7t3.backend.dto.ProvincesDTO;
import com.finalprojectc7t3.backend.entity.Characteristics;
import com.finalprojectc7t3.backend.entity.Provinces;
import com.finalprojectc7t3.backend.excepciones.DontFindException;
import com.finalprojectc7t3.backend.repository.ICharacteristicsRepository;
import com.finalprojectc7t3.backend.repository.IProvincesRepository;
import com.finalprojectc7t3.backend.service.IProvincesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvincesService implements IProvincesService {

    private final IProvincesRepository repository;
    private final ObjectMapper mapper;

        public ProvincesService(IProvincesRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;}


    @Override
    public ProvincesDTO create(ProvincesDTO provincesDTO) throws DontFindException {

        return mapper.convertValue(repository.save(mapper.convertValue(provincesDTO, Provinces.class)), ProvincesDTO.class);
    }

    @Override
    public ProvincesDTO update(ProvincesDTO provincesDTO) throws DontFindException {
      return create(provincesDTO);
    }

    @Override
    public List<ProvincesDTO> findAll() {
        return repository.findAll().stream().map(i -> mapper.convertValue(i, ProvincesDTO.class)).collect(Collectors.toList());
    }


    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }


    @Override
    public ProvincesDTO findById(Integer provinceId) {
        return mapper.convertValue(repository.findById(provinceId).orElse(null), ProvincesDTO.class);
    }
}

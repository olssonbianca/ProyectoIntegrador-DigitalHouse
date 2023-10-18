package com.finalprojectc7t3.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalprojectc7t3.backend.dto.CharacteristicDTO;
import com.finalprojectc7t3.backend.entity.Characteristics;
import com.finalprojectc7t3.backend.excepciones.DontFindException;
import com.finalprojectc7t3.backend.repository.ICharacteristicsRepository;
import com.finalprojectc7t3.backend.service.ICharacteristicsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharacteristicsService implements ICharacteristicsService {

    private final ICharacteristicsRepository repository;
    private final ObjectMapper mapper;

        public CharacteristicsService(ICharacteristicsRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;}


    @Override
    public CharacteristicDTO create(CharacteristicDTO characteristicDTO) throws DontFindException {

        return mapper.convertValue(repository.save(mapper.convertValue(characteristicDTO, Characteristics.class)), CharacteristicDTO.class);
    }

    @Override
    public CharacteristicDTO update(CharacteristicDTO characteristicDTO) throws DontFindException {
      return create(characteristicDTO);
    }

    @Override
    public List<CharacteristicDTO> findAll() {
        return repository.findAll().stream().map(i -> mapper.convertValue(i, CharacteristicDTO.class)).collect(Collectors.toList());
    }


    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }


    @Override
    public CharacteristicDTO findById(Integer characteristicId) {
        return mapper.convertValue(repository.findById(characteristicId).orElse(null), CharacteristicDTO.class);
    }
}

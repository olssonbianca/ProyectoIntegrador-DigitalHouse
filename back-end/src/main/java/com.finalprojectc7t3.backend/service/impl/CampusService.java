package com.finalprojectc7t3.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalprojectc7t3.backend.dto.CampusDTO;
import com.finalprojectc7t3.backend.entity.Campus;
import com.finalprojectc7t3.backend.excepciones.DontFindException;
import com.finalprojectc7t3.backend.repository.ICampusRepository;
import com.finalprojectc7t3.backend.service.ICampusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampusService implements ICampusService {

    private final ICampusRepository repository;
    private final ObjectMapper mapper;

        public CampusService( ICampusRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;}


    @Override
    public CampusDTO create(CampusDTO campusDTO) throws DontFindException {

        return mapper.convertValue(repository.save(mapper.convertValue(campusDTO, Campus.class)), CampusDTO.class);
    }

    @Override
    public CampusDTO update(CampusDTO campusDTO) throws DontFindException {
      return create(campusDTO);
    }

    @Override
    public List<CampusDTO> findAll() {
        return repository.findAll().stream().map(i -> mapper.convertValue(i, CampusDTO.class)).collect(Collectors.toList());
    }


    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }


    @Override
    public CampusDTO findById(Integer campusId) {
        return mapper.convertValue(repository.findById(campusId).orElse(null), CampusDTO.class);
    }
}

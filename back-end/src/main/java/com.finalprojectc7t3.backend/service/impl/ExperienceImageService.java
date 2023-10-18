package com.finalprojectc7t3.backend.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalprojectc7t3.backend.dto.ExperienceImageDTO;
import com.finalprojectc7t3.backend.entity.ExperienceImage;
import com.finalprojectc7t3.backend.repository.IExperienceImageRepository;
import com.finalprojectc7t3.backend.service.IExperienceImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExperienceImageService implements IExperienceImageService {

    private final IExperienceImageRepository repository;
    private final ObjectMapper mapper;

    @Autowired
    public ExperienceImageService(IExperienceImageRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public ExperienceImageDTO create(ExperienceImageDTO experienceImageDTO) {
        experienceImageDTO.setIsEnabled(experienceImageDTO.getIsEnabled() == null || experienceImageDTO.getIsEnabled());
        Optional<ExperienceImageDTO> saved = Optional.ofNullable(findByUnique(experienceImageDTO.getExperienceId(), experienceImageDTO.getImageId()));
        saved.ifPresent(item -> experienceImageDTO.setExperienceImageId(item.getExperienceImageId()));
        return mapper.convertValue(repository.save(mapper.convertValue(experienceImageDTO, ExperienceImage.class)), ExperienceImageDTO.class);
    }

    @Override
    public List<ExperienceImageDTO> findAll() {
        return repository.findAll().stream().map(i -> mapper.convertValue(i, ExperienceImageDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public ExperienceImageDTO findByUnique(Long experienceId, Integer idImage) {
        return mapper.convertValue(repository.findByExperienceIdAndImageId(experienceId, idImage), ExperienceImageDTO.class);
    }

    @Override
    public ExperienceImageDTO update(ExperienceImageDTO experienceImageDTO) {
        return create(experienceImageDTO);
    }

}

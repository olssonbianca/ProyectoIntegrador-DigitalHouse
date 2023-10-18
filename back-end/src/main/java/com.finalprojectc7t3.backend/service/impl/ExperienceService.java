package com.finalprojectc7t3.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalprojectc7t3.backend.dto.ExperienceCharacteristicDTO;
import com.finalprojectc7t3.backend.dto.ExperienceCreateDTO;
import com.finalprojectc7t3.backend.dto.ExperienceDTO;
import com.finalprojectc7t3.backend.dto.ExperienceImageDTO;
import com.finalprojectc7t3.backend.entity.Experience;
import com.finalprojectc7t3.backend.excepciones.DontFindException;
import com.finalprojectc7t3.backend.repository.IExperienceRepository;
import com.finalprojectc7t3.backend.service.IExperienceCharacteristicService;
import com.finalprojectc7t3.backend.service.IExperienceImageService;
import com.finalprojectc7t3.backend.service.IExperienceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExperienceService implements IExperienceService {

    private static final Logger LOGGER = Logger.getLogger(ExperienceService.class);

    private IExperienceRepository iExperienceRepository;
    private IExperienceImageService experienceImageService;

    private IExperienceCharacteristicService iExperienceCharacteristicService;

    public ExperienceService(IExperienceRepository iExperienceRepository, IExperienceImageService experienceImageService, IExperienceCharacteristicService iExperienceCharacteristicService) {
        this.iExperienceRepository = iExperienceRepository;
        this.experienceImageService = experienceImageService;
        this.iExperienceCharacteristicService = iExperienceCharacteristicService;
    }

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private Validator validator;

    @Override
    public ExperienceCreateDTO createExperience(ExperienceDTO experienceCreateDTO) {
        LOGGER.info("... ... From ExperienceService class, createExperience method is running ... ...");

        experienceCreateDTO.setIsEnabled(experienceCreateDTO.getIsEnabled() != null ? experienceCreateDTO.getIsEnabled() : true);
        Experience newExperienceEntityToSave = mapper.convertValue(experienceCreateDTO, Experience.class);
        ExperienceCreateDTO experienceDTOsaved = mapper.convertValue(
                iExperienceRepository.save(newExperienceEntityToSave), ExperienceCreateDTO.class);
        LOGGER.info("Creating a new experience: " + experienceDTOsaved.getExperienceName());


        experienceCreateDTO.getImagesToSaved().ifPresent(imagesId -> imagesId.forEach(id -> {
            ExperienceImageDTO imagesExperience = ExperienceImageDTO.builder()
                    .experienceId(experienceDTOsaved.getExperienceId())
                    .imageId(id)
                    .build();
            try {
                experienceImageService.create(imagesExperience);
            } catch (DontFindException e) {
                throw new RuntimeException(e);
            }
        }));

        experienceCreateDTO.getCharacteristicListToSaved().ifPresent(characteristicId -> characteristicId.forEach(id -> {
            ExperienceCharacteristicDTO characteristicExperience = ExperienceCharacteristicDTO.builder()
                    .experienceId(experienceDTOsaved.getExperienceId())
                    .characteristicId(id)
                    .build();
            try {
                iExperienceCharacteristicService.create(characteristicExperience);
            } catch (DontFindException e) {
                throw new RuntimeException(e);
            }
        }));

        return experienceDTOsaved;
    }

    @Override
    public ExperienceDTO getExperienceById(Integer id) {
        LOGGER.info("... ... From ExperienceService class, getExperienceById method is running ... ...");
        Optional<Experience> experienceEntityFound = iExperienceRepository.findById(id);
        ExperienceDTO experienceDTOFound = null;

        if(!experienceEntityFound.isPresent()){
            LOGGER.info("Service: ResourceNotFoundException was thrown trying to get an experience which is null, experience id: " + id + ".");
        }else{
            experienceDTOFound = mapper.convertValue(experienceEntityFound, ExperienceDTO.class);
            LOGGER.info("Getting experience id: " + id);
        }
        return experienceDTOFound;
    }

    @Override
    public Set<ExperienceDTO> getAllExperiences() {
        LOGGER.info("### From ExperienceService class, getAllExperiences method is running ###");
        List<Experience> experienceEntityList = iExperienceRepository.findAll().stream().filter(Experience::getIsEnabled).toList();
        Set<ExperienceDTO> experienceDtoSet = new HashSet<>();
        for (Experience experience: experienceEntityList){
            experienceDtoSet.add(mapper.convertValue(experience, ExperienceDTO.class));
        }
        LOGGER.info("Getting all experiences");
        return experienceDtoSet;
    }

    @Override
    public ExperienceDTO updateExperience(ExperienceDTO experienceCreateDTO) {
        experienceCreateDTO.setIsEnabled(experienceCreateDTO.getIsEnabled() != null ? experienceCreateDTO.getIsEnabled() : true);
        return mapper.convertValue(iExperienceRepository.save(mapper.convertValue(experienceCreateDTO, Experience.class)),ExperienceDTO.class);
    }

    @Override
    public void deleteExperienceById(Integer id) {
        LOGGER.info("********** From ExperienceService class, deleteExperienceById method is running **********");
        ExperienceDTO experienceDTO = getExperienceById(id);
        if(experienceDTO != null){
            experienceDTO.setIsEnabled(false);
            iExperienceRepository.save(mapper.convertValue(experienceDTO, Experience.class));
        }
    }

    @Override
    public List<ExperienceDTO> getExperienceByCategory(Long categoryId) {
        return iExperienceRepository.findAllByCategoryIdAndIsEnabledTrue(categoryId)
                .stream()
                .map(ex -> mapper.convertValue(ex, ExperienceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ExperienceDTO> getExperienceByRandom() {
        return iExperienceRepository.findRandom().stream()
                .map(ex -> mapper.convertValue(ex, ExperienceDTO.class))
                .collect(Collectors.toList());
    }
}

package com.finalprojectc7t3.backend.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalprojectc7t3.backend.dto.ImageDTO;
import com.finalprojectc7t3.backend.entity.Image;
import com.finalprojectc7t3.backend.repository.IImageRepository;
import com.finalprojectc7t3.backend.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImageService implements IImageService {

    private final IImageRepository repository;
    private final S3ServiceImpl s3Service;
    private final ObjectMapper mapper;

    @Autowired
    public ImageService(IImageRepository repository, S3ServiceImpl s3Service, ObjectMapper mapper) {
        this.repository = repository;
        this.s3Service = s3Service;
        this.mapper = mapper;
    }


    @Override
    public ImageDTO create(ImageDTO imageDto) {
        imageDto.setIsEnabled(imageDto.getIsEnabled() != null ? imageDto.getIsEnabled() : true);
        Optional<ImageDTO> saved = findImagenByKey(imageDto.getKeyImage());
        saved.ifPresent(imagen -> imageDto.setImageId(imagen.getImageId()));
        return mapper.convertValue(repository.save(mapper.convertValue(imageDto, Image.class)), ImageDTO.class);
    }

    @Override
    public List<ImageDTO> findAll() {
        return repository.findAll().stream().map(i -> mapper.convertValue(i, ImageDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public ImageDTO update(ImageDTO imagenDto) {
        return create(imagenDto);
    }

    @Override
    public Optional<ImageDTO> findImagenByKey(String key) {
        return Optional.ofNullable(mapper.convertValue(repository.findByKeyImage(key), ImageDTO.class));
    }

    @Override
    public ImageDTO savedImageS3(MultipartFile imagen) throws IOException {
        String key = s3Service.saveImageInS3(imagen);
        ImageDTO imagenDto = ImageDTO.builder()
                .keyImage(key)
                .urlImage(s3Service.obtenerUrlS3(key))
                .isEnabled(true)
                .build();
        return create(imagenDto);
    }


    @Override
    public void deleteImageS3(String key) {
        s3Service.deleteImageS3(key);
        Optional<ImageDTO> imagenGuardada = findImagenByKey(key);
        imagenGuardada.ifPresent(image -> {
            image.setIsEnabled(false);
            update(image);
        });
    }

}

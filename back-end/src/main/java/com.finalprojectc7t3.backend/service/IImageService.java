package com.finalprojectc7t3.backend.service;


import com.finalprojectc7t3.backend.dto.ImageDTO;
import com.finalprojectc7t3.backend.excepciones.DontFindException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IImageService {
    ImageDTO create(ImageDTO imageDTO) throws DontFindException;

    ImageDTO update(ImageDTO imageDTO) throws DontFindException;

    List<ImageDTO> findAll();

    Optional<ImageDTO> findImagenByKey(String key);

    void delete(Integer id);

    ImageDTO savedImageS3(MultipartFile imagen) throws IOException;
    void deleteImageS3(String key);
}

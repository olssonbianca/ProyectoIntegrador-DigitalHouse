package com.finalprojectc7t3.backend.service;

import com.finalprojectc7t3.backend.dto.ProvincesDTO;
import com.finalprojectc7t3.backend.excepciones.DontFindException;

import java.util.List;

public interface IProvincesService {

    ProvincesDTO create(ProvincesDTO provincesDTO) throws DontFindException;

    ProvincesDTO update(ProvincesDTO provincesDTO) throws DontFindException;

    List<ProvincesDTO> findAll();

    void delete(Integer id);

    ProvincesDTO findById(Integer provinceId);
}

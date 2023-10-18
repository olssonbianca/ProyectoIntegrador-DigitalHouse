package com.finalprojectc7t3.backend.service;


import com.finalprojectc7t3.backend.dto.CityDTO;
import com.finalprojectc7t3.backend.excepciones.DontFindException;

import java.util.List;
import java.util.Optional;

public interface ICityService {
    CityDTO create(CityDTO cityDTO) throws DontFindException;
    CityDTO update(CityDTO cityDTO) throws DontFindException;
    List<CityDTO> findAll();
    void delete(Integer id);
    CityDTO findById(Integer id);
}

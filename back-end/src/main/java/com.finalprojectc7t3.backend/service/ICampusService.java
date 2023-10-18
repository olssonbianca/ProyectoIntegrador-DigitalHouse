package com.finalprojectc7t3.backend.service;

import com.finalprojectc7t3.backend.dto.CampusDTO;
import com.finalprojectc7t3.backend.excepciones.DontFindException;

import java.util.List;

public interface ICampusService {

    CampusDTO create(CampusDTO campusDTO) throws DontFindException;

    CampusDTO update(CampusDTO campusDTO) throws DontFindException;

    List<CampusDTO> findAll();

    void delete(Integer id);

    CampusDTO findById(Integer campusId);
}

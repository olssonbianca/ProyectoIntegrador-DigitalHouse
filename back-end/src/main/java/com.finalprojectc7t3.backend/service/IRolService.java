package com.finalprojectc7t3.backend.service;

import com.finalprojectc7t3.backend.dto.RolDTO;
import com.finalprojectc7t3.backend.excepciones.DontFindException;

import java.util.List;

public interface IRolService {
    RolDTO create(RolDTO rolDTO) throws DontFindException;

    RolDTO update(RolDTO rolDTO) throws DontFindException;

    List<RolDTO> findAll();

    RolDTO findByCode (String code) throws DontFindException;

    void delete(Integer idRol);
}

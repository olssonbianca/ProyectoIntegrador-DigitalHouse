package com.finalprojectc7t3.backend.service;

import com.finalprojectc7t3.backend.dto.CharacteristicDTO;
import com.finalprojectc7t3.backend.excepciones.DontFindException;

import java.util.List;

public interface ICharacteristicsService {

    CharacteristicDTO create(CharacteristicDTO characteristicDTO) throws DontFindException;

    CharacteristicDTO update(CharacteristicDTO characteristicDTO) throws DontFindException;

    List<CharacteristicDTO> findAll();

    void delete(Integer id);

    CharacteristicDTO findById(Integer characteristicId);
}

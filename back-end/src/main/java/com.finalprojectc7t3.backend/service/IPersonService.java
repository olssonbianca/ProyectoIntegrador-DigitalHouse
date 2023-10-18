package com.finalprojectc7t3.backend.service;

import com.finalprojectc7t3.backend.dto.PersonDTO;
import com.finalprojectc7t3.backend.excepciones.DontFindException;
import java.util.List;

public interface IPersonService {

    PersonDTO create(PersonDTO personDTO) throws DontFindException;

    PersonDTO update(PersonDTO personDTO) throws DontFindException;

    List<PersonDTO> findAll();

    PersonDTO findByMail (String mail) throws DontFindException;

    void delete(Integer idPerson);

}
package com.finalprojectc7t3.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalprojectc7t3.backend.dto.PersonDTO;
import com.finalprojectc7t3.backend.entity.Person;
import com.finalprojectc7t3.backend.excepciones.DontFindException;
import com.finalprojectc7t3.backend.repository.IPersonRepository;
import com.finalprojectc7t3.backend.service.IPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PersonService implements IPersonService {

    private final IPersonRepository repository;
    private final ObjectMapper mapper;

    @Override
    public PersonDTO create(PersonDTO personDTO) throws DontFindException {
        Optional<Person> person = repository.findByMail(personDTO.getMail());
        person.ifPresent(p -> personDTO.setIdPerson(p.getIdPerson()));
        return mapper.convertValue(repository.save(mapper.convertValue(personDTO, Person.class)), PersonDTO.class);
    }

    @Override
    public PersonDTO update(PersonDTO personDTO) throws DontFindException {
        return null;
    }

    @Override
    public List<PersonDTO> findAll() {
        return null;
    }

    @Override
    public PersonDTO findByMail(String mail) throws DontFindException {
        return null;
    }

    @Override
    public void delete(Integer idPerson) {

    }
}

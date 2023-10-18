package com.finalprojectc7t3.backend.repository;

import com.finalprojectc7t3.backend.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IPersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person>  findByMail (String mail);

}

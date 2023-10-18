package com.finalprojectc7t3.backend.repository;

import com.finalprojectc7t3.backend.entity.Characteristics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICharacteristicsRepository extends JpaRepository<Characteristics, Integer> {

}

package com.finalprojectc7t3.backend.repository;

import com.finalprojectc7t3.backend.entity.Provinces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvincesRepository extends JpaRepository<Provinces, Integer> {

}

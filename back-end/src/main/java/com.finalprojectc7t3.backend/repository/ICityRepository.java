package com.finalprojectc7t3.backend.repository;


import com.finalprojectc7t3.backend.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICityRepository extends JpaRepository<City, Integer> {
}

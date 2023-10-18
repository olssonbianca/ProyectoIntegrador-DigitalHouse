package com.finalprojectc7t3.backend.repository;


import com.finalprojectc7t3.backend.entity.Campus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICampusRepository extends JpaRepository<Campus, Integer> {
}

package com.finalprojectc7t3.backend.repository;

import com.finalprojectc7t3.backend.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IRolRepository extends JpaRepository <Rol, Integer> {

    Rol findByCode (String code);
}

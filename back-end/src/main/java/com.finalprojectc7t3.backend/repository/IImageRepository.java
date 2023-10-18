package com.finalprojectc7t3.backend.repository;


import com.finalprojectc7t3.backend.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImageRepository extends JpaRepository<Image, Integer> {
    Image findByKeyImage(String key);
}

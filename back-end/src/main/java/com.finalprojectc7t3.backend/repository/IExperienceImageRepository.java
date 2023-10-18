package com.finalprojectc7t3.backend.repository;
import com.finalprojectc7t3.backend.entity.ExperienceImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IExperienceImageRepository extends JpaRepository<ExperienceImage, Integer> {
    ExperienceImage findByExperienceIdAndImageId(Long experienceId, Integer imageId);
}

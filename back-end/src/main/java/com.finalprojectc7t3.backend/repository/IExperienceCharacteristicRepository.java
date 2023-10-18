package com.finalprojectc7t3.backend.repository;
import com.finalprojectc7t3.backend.entity.ExperienceCharacteristics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExperienceCharacteristicRepository extends JpaRepository<ExperienceCharacteristics, Integer> {
    ExperienceCharacteristics findByExperienceIdAndCharacteristicId(Long experienceId, Integer characteristicId);
}

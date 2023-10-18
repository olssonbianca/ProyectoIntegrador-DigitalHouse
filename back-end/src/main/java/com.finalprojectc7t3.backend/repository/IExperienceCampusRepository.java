package com.finalprojectc7t3.backend.repository;
import com.finalprojectc7t3.backend.entity.ExperienceCampus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExperienceCampusRepository extends JpaRepository<ExperienceCampus, Integer> {
    ExperienceCampus findByExperienceIdAndCampusId(Long experienceId, Integer campusId);
}

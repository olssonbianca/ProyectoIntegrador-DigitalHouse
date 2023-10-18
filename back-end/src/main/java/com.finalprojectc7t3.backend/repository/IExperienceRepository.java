package com.finalprojectc7t3.backend.repository;

import com.finalprojectc7t3.backend.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExperienceRepository extends JpaRepository<Experience, Integer> {
    List<Experience> findAllByCategoryIdAndIsEnabledTrue(Long categoryId);

    @Query(nativeQuery=true, value="SELECT *  FROM experience WHERE isEnabled is true ORDER BY RAND() LIMIT 10")
    List<Experience> findRandom();
}

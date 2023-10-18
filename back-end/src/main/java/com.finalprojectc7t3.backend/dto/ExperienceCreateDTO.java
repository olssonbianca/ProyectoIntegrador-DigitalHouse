package com.finalprojectc7t3.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceCreateDTO {
    private Long experienceId;
    private String experienceName;
    private String description;
    private Boolean isEnabled;
}

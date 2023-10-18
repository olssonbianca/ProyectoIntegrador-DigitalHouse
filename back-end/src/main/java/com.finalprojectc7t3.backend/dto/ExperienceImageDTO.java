package com.finalprojectc7t3.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceImageDTO {
    private Long experienceImageId;
    private Long experienceId;
    private Integer imageId;
    private Boolean isEnabled;
}

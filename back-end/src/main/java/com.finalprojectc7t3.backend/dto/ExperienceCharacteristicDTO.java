package com.finalprojectc7t3.backend.dto;

import com.finalprojectc7t3.backend.entity.Characteristics;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceCharacteristicDTO {
    private Integer experienceCharacteristicId;
    private Integer characteristicId;
    private Long experienceId;

    private Characteristics characteristics;
}

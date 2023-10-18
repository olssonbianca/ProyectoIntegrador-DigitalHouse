package com.finalprojectc7t3.backend.dto;

import com.finalprojectc7t3.backend.entity.ExperienceImagePartial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDTO {

    private Integer experienceId;
    private String experienceName;
    private Long categoryId;
    private String description;
    private Boolean isEnabled;

    List<ExperienceImagePartial> experienceImageList;
    private List<String> characteristicsList;
    List<Integer> imagesToSaved;
    List<Integer> characteristicListToSaved;

    public Optional<List<Integer>> getImagesToSaved() {
        return Optional.ofNullable(imagesToSaved);
    }

    public Optional<List<Integer>> getCharacteristicListToSaved() {
        return Optional.ofNullable(characteristicListToSaved);
    }
}

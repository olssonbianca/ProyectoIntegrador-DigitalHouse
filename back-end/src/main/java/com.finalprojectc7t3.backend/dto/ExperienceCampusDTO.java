package com.finalprojectc7t3.backend.dto;

import com.finalprojectc7t3.backend.entity.Campus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceCampusDTO {

    private Integer experienceCampusId;
    private Integer campusId;
    private Long experienceId;
    private Boolean isEnabled;

    private Campus campus;
}

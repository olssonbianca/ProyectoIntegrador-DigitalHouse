package com.finalprojectc7t3.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateDateDto {
    private Integer experienceId;
    private LocalDate from;
    private LocalDate to;
}

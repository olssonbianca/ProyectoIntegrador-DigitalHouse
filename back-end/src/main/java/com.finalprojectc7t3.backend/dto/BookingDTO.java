package com.finalprojectc7t3.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Integer bookingId;
    private LocalDate entryDate;
    private LocalDate departureDate;
    private int idUser;
    private Integer experienceId;

    private ExperienceDTO experience;

}
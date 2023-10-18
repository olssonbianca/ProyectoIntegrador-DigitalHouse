package com.finalprojectc7t3.backend.service.impl;

import com.finalprojectc7t3.backend.dto.BookingDTO;
import com.finalprojectc7t3.backend.dto.ExperienceDTO;
import com.finalprojectc7t3.backend.service.IResumable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ResumableBookingInformationService implements IResumable<BookingDTO> {

    private final BookingService bookingService;
    private final ExperienceService experienceService;

    @Override
    public BookingDTO resumeInformation(Integer id) {

        BookingDTO bookingDTO = bookingService.findById(id);
        ExperienceDTO experienceDTO = experienceService.getExperienceById(bookingDTO.getExperienceId());
        bookingDTO.setExperience(experienceDTO);

        return bookingDTO;
    }
}

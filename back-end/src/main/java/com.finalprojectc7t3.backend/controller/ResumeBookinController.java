package com.finalprojectc7t3.backend.controller;

import com.finalprojectc7t3.backend.dto.BookingDTO;
import com.finalprojectc7t3.backend.service.IResumable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeBookinController {

    private final IResumable<BookingDTO> resumeBooking;

    @GetMapping("/booking/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        BookingDTO bookingDTO = resumeBooking.resumeInformation(id);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }
}

package com.finalprojectc7t3.backend.service;

import com.finalprojectc7t3.backend.dto.BookingDTO;
import com.finalprojectc7t3.backend.dto.ResponseDateDto;
import com.finalprojectc7t3.backend.dto.ValidateDateDto;
import com.finalprojectc7t3.backend.excepciones.DontFindException;

import java.time.LocalDate;
import java.util.List;

public interface IBookingService {

    BookingDTO create(BookingDTO bookingDTO) throws DontFindException;

    BookingDTO update(BookingDTO bookingDTO) throws DontFindException;

    List<BookingDTO> findAll();

    void delete(Integer id);

    BookingDTO findById(Integer bookingId);

    BookingDTO lookUp(Integer idUser, Integer experienceId, LocalDate entryDate, LocalDate departureDate );

    ResponseDateDto validateDateBooking(ValidateDateDto validateDateDto);
}

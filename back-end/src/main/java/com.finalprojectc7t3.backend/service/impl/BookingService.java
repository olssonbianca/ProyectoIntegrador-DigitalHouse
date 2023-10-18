package com.finalprojectc7t3.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalprojectc7t3.backend.dto.BookingDTO;
import com.finalprojectc7t3.backend.dto.ResponseDateDto;
import com.finalprojectc7t3.backend.dto.ValidateDateDto;
import com.finalprojectc7t3.backend.entity.Booking;
import com.finalprojectc7t3.backend.excepciones.DontFindException;
import com.finalprojectc7t3.backend.repository.IBookingRepository;
import com.finalprojectc7t3.backend.service.IBookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class BookingService implements IBookingService {

    private final IBookingRepository repository;
    private final ObjectMapper mapper;

    public BookingService(IBookingRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public BookingDTO create(BookingDTO bookingDTO) throws DontFindException {
        BookingDTO buscar = lookUp(bookingDTO.getIdUser(), bookingDTO.getExperienceId(), bookingDTO.getEntryDate(), bookingDTO.getDepartureDate());
        if (buscar != null) {
            try {
                throw new Exception("El registro ya existe");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return mapper.convertValue(repository.save(mapper.convertValue(bookingDTO, Booking.class)), BookingDTO.class);
    }

    @Override
    public BookingDTO update(BookingDTO bookingDTO) throws DontFindException {
        return create(bookingDTO);
    }

    @Override
    public List<BookingDTO> findAll() {
        return repository.findAll().stream().map(i -> mapper.convertValue(i, BookingDTO.class)).collect(Collectors.toList());
    }


    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }


    @Override
    public BookingDTO findById(Integer bookingId) {
        return mapper.convertValue(repository.findById(bookingId).orElse(null), BookingDTO.class);
    }

    @Override
    public BookingDTO lookUp(Integer idUser, Integer experienceId, LocalDate entryDate, LocalDate departureDate) {
        return mapper.convertValue(repository.findByIdUserAndExperienceIdAndEntryDateAndDepartureDate(idUser, experienceId, entryDate, departureDate), BookingDTO.class);
    }

    @Override
    public ResponseDateDto validateDateBooking(ValidateDateDto requestDto) {
        List<String> datesResponse = new ArrayList<>();

        repository.findByExperienceIdAndEntryDateIsGreaterThanEqualAndDepartureDateIsLessThanEqual(
                        requestDto.getExperienceId(), requestDto.getFrom(), requestDto.getTo()
                )
                .forEach(booking -> datesResponse.addAll(concatenateDatesToString(booking.getEntryDate(), booking.getDepartureDate())));

        return ResponseDateDto.builder()
                .dateRanges(datesResponse)
                .build();
    }

    private List<String> concatenateDatesToString(LocalDate from, LocalDate to) {
        List<String> dateListConcatenated = new ArrayList<>();
        long noOfDaysBetween = DAYS.between(from, to);
        for (int i = 0; i <= noOfDaysBetween; i++) {
            dateListConcatenated.add(String.valueOf(from.plusDays(i)));
        }
        return dateListConcatenated;
    }
}

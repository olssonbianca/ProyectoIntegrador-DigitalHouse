package com.finalprojectc7t3.backend.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalprojectc7t3.backend.dto.BookingDTO;
import com.finalprojectc7t3.backend.dto.ValidateDateDto;
import com.finalprojectc7t3.backend.excepciones.DontFindException;
import com.finalprojectc7t3.backend.service.impl.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/booking")

public class BookingController {

    private BookingService bookingService;
    private ObjectMapper mapper;

    @Autowired
    public BookingController(BookingService service){
        this.bookingService = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody BookingDTO bookingDTO) throws DontFindException {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.create(bookingDTO));
    }

    @PostMapping("/invalidDates")
    public ResponseEntity<?> availableDates(@Valid @RequestBody ValidateDateDto requestDate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.validateDateBooking(requestDate));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        bookingService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(bookingService.findAll(),null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        BookingDTO bookingDTO = bookingService.findById(id);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

}

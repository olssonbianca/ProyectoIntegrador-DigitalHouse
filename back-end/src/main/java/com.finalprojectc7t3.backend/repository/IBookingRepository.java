package com.finalprojectc7t3.backend.repository;

import com.finalprojectc7t3.backend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Integer> {
    Booking findByIdUserAndExperienceIdAndEntryDateAndDepartureDate(Integer idUser, Integer experienceId, LocalDate entryDate, LocalDate departureDate );
    List<Booking> findByExperienceIdAndEntryDateIsGreaterThanEqualAndDepartureDateIsLessThanEqual(Integer experienceId, LocalDate from, LocalDate to);
}

package com.patika.veterinaryClinic.repository;

import com.patika.veterinaryClinic.entity.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate, Long> {

    boolean existsByAvailableDateAndDoctorId(LocalDate date, Long doctorId);

    List<AvailableDate> findByDoctorId(Long doctorId);

    boolean existsByAvailableDateAndDoctorIdAndId(LocalDate date, Long doctorId, Long id); //Update için 'aynı tarih ve doktor var ancak gelen requeste değil' kontrolü.

    boolean existsByDoctorIdAndAvailableDate(Long doctorId, LocalDate appointmentDay);
}


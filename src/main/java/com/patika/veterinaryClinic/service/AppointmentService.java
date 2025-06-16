package com.patika.veterinaryClinic.service;

import com.patika.veterinaryClinic.dto.request.AppointmentRequestDto;
import com.patika.veterinaryClinic.dto.response.AppointmentResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {
    AppointmentResponseDto save(AppointmentRequestDto request);

    AppointmentResponseDto update(Long id, AppointmentRequestDto request);

    void delete(Long id);

    List<AppointmentResponseDto> getByDoctorIdAndDateRange(Long doctorId, LocalDateTime start, LocalDateTime end);

    List<AppointmentResponseDto> getByAnimalIdAndDateRange(Long animalId, LocalDateTime start, LocalDateTime end);
}

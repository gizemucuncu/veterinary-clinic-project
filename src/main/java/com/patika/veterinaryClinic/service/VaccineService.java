package com.patika.veterinaryClinic.service;

import com.patika.veterinaryClinic.dto.request.VaccineRequestDto;
import com.patika.veterinaryClinic.dto.response.VaccineResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface VaccineService {
    VaccineResponseDto save(VaccineRequestDto request);

    VaccineResponseDto update(Long id, VaccineRequestDto request);

    void delete(Long id);

    VaccineResponseDto getById(Long id);

    List<VaccineResponseDto> getAll();

    List<VaccineResponseDto> getByProtectionDateRange(LocalDate startDate, LocalDate endDate);

    List<VaccineResponseDto> getAllByAnimalId(Long animalId);
}

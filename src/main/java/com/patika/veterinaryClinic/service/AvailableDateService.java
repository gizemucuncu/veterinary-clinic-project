package com.patika.veterinaryClinic.service;

import com.patika.veterinaryClinic.dto.request.AvailableDateRequestDto;
import com.patika.veterinaryClinic.dto.response.AvailableDateResponseDto;

import java.util.List;

public interface AvailableDateService {
    AvailableDateResponseDto save(AvailableDateRequestDto request);

    List<AvailableDateResponseDto> getAllByDoctorId(Long doctorId);

    AvailableDateResponseDto getById(Long id);


    AvailableDateResponseDto update(Long id, AvailableDateRequestDto request);

    void delete(Long id);
}

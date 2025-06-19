package com.patika.veterinaryClinic.service.Interface;

import com.patika.veterinaryClinic.dto.request.DoctorRequestDto;
import com.patika.veterinaryClinic.dto.response.DoctorListResponseDto;
import com.patika.veterinaryClinic.dto.response.DoctorResponseDto;
import com.patika.veterinaryClinic.entity.Doctor;

import java.util.List;

public interface DoctorService {
    DoctorResponseDto save(DoctorRequestDto request);

    DoctorResponseDto update(Long id, DoctorRequestDto request);

    DoctorResponseDto getById(Long id);

    void delete(Long id);

    List<DoctorListResponseDto> getAll();

    Doctor getEntityById(Long id);

}

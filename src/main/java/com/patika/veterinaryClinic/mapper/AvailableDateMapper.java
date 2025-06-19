package com.patika.veterinaryClinic.mapper;

import com.patika.veterinaryClinic.dto.request.AvailableDateRequestDto;
import com.patika.veterinaryClinic.dto.response.AvailableDateResponseDto;
import com.patika.veterinaryClinic.entity.AvailableDate;
import com.patika.veterinaryClinic.service.Interface.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AvailableDateMapper {
    private final DoctorService doctorService;

    public AvailableDate toEntity(AvailableDateRequestDto dto) {
        AvailableDate entity = new AvailableDate();
        entity.setAvailableDate(dto.getAvailableDate());
        entity.setDoctor(doctorService.getEntityById(dto.getDoctorId())); // Nested çözüm
        return entity;
    }

    public AvailableDateResponseDto toDto(AvailableDate entity) {
        AvailableDateResponseDto dto = new AvailableDateResponseDto();
        dto.setId(entity.getId());
        dto.setAvailableDate(entity.getAvailableDate());
        dto.setDoctorId(entity.getDoctor().getId());
        dto.setDoctorName(entity.getDoctor().getName());
        return dto;
    }
}

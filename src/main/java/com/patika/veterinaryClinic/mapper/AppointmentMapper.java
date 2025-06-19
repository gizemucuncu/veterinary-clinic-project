package com.patika.veterinaryClinic.mapper;

import com.patika.veterinaryClinic.dto.request.AppointmentRequestDto;
import com.patika.veterinaryClinic.dto.response.AppointmentResponseDto;
import com.patika.veterinaryClinic.entity.Appointment;
import com.patika.veterinaryClinic.service.Interface.AnimalService;
import com.patika.veterinaryClinic.service.Interface.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentMapper {
    private final DoctorService doctorService;
    private final AnimalService animalService;

    public Appointment toEntity(AppointmentRequestDto dto) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setDoctor(doctorService.getEntityById(dto.getDoctorId()));
        appointment.setAnimal(animalService.getEntityById(dto.getAnimalId()));
        return appointment;
    }

    public AppointmentResponseDto toDto(Appointment entity) {
        AppointmentResponseDto dto = new AppointmentResponseDto();

        dto.setId(entity.getId());
        dto.setAppointmentDate(entity.getAppointmentDate());

        dto.setDoctorId(entity.getDoctor().getId());
        dto.setDoctorName(entity.getDoctor().getName());

        dto.setAnimalId(entity.getAnimal().getId());
        dto.setAnimalName(entity.getAnimal().getName());
        return dto;
    }
}

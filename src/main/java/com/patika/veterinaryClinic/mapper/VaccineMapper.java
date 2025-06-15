package com.patika.veterinaryClinic.mapper;

import com.patika.veterinaryClinic.dto.request.VaccineRequestDto;
import com.patika.veterinaryClinic.dto.response.VaccineResponseDto;
import com.patika.veterinaryClinic.entity.Animal;
import com.patika.veterinaryClinic.entity.Vaccine;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VaccineMapper {
    private final ModelMapper modelMapper;

    public VaccineMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Vaccine toEntity(VaccineRequestDto dto, Animal animal) {
        Vaccine vaccine = modelMapper.map(dto, Vaccine.class);
        vaccine.setAnimal(animal);
        return vaccine;
    }

    public VaccineResponseDto toDto(Vaccine vaccine) {
        VaccineResponseDto dto = modelMapper.map(vaccine, VaccineResponseDto.class);
        if (vaccine.getAnimal() != null) {
            dto.setAnimalId(vaccine.getAnimal().getId());
            dto.setAnimalName(vaccine.getAnimal().getName());
        }
        return dto;
    }
}

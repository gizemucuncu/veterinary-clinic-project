package com.patika.veterinaryClinic.mapper;

import com.patika.veterinaryClinic.dto.request.AnimalRequestDto;
import com.patika.veterinaryClinic.dto.response.AnimalResponseDto;
import com.patika.veterinaryClinic.entity.Animal;
import com.patika.veterinaryClinic.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AnimalMapper {

    private final ModelMapper modelMapper;

    public AnimalMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AnimalResponseDto toDto(Animal animal) {
        AnimalResponseDto dto = modelMapper.map(animal, AnimalResponseDto.class);
        if (animal.getCustomer() != null) {
            dto.setCustomerId(animal.getCustomer().getId());
            dto.setCustomerName(animal.getCustomer().getName());
        }
        return dto;
    }

    public Animal toEntity(AnimalRequestDto dto, Customer customer) {
        Animal animal = modelMapper.map(dto, Animal.class);
        animal.setCustomer(customer);
        return animal;
    }
}

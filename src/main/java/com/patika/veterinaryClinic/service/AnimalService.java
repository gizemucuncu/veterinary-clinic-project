package com.patika.veterinaryClinic.service;

import com.patika.veterinaryClinic.dto.request.AnimalRequestDto;
import com.patika.veterinaryClinic.dto.response.AnimalResponseDto;
import com.patika.veterinaryClinic.entity.Animal;

import java.util.List;

public interface AnimalService {
    AnimalResponseDto save(AnimalRequestDto request);

    AnimalResponseDto update(Long id, AnimalRequestDto request);

    void delete(Long id);

    AnimalResponseDto getById(Long id);

    List<AnimalResponseDto> getAll();

    List<AnimalResponseDto> getByCustomerId(Long customerId);

    List<AnimalResponseDto> searchByName(String name);

    Animal getEntityById(Long id);
}

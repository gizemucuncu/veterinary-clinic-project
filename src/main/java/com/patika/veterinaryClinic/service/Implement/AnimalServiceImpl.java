package com.patika.veterinaryClinic.service.Implement;

import com.patika.veterinaryClinic.dto.request.AnimalRequestDto;
import com.patika.veterinaryClinic.dto.response.AnimalResponseDto;
import com.patika.veterinaryClinic.dto.response.DoctorResponseDto;
import com.patika.veterinaryClinic.entity.Animal;
import com.patika.veterinaryClinic.entity.Customer;
import com.patika.veterinaryClinic.entity.Doctor;
import com.patika.veterinaryClinic.exception.AlreadyExistsException;
import com.patika.veterinaryClinic.exception.message.ErrorMessage;
import com.patika.veterinaryClinic.mapper.AnimalMapper;
import com.patika.veterinaryClinic.repository.AnimalRepo;
import com.patika.veterinaryClinic.repository.CustomerRepo;
import com.patika.veterinaryClinic.service.AnimalService;
import com.patika.veterinaryClinic.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepo animalRepo;
    private final CustomerRepo customerRepo;
    private final ModelMapper modelMapper;
    private final AnimalMapper animalMapper;

    @Override
    public AnimalResponseDto save(AnimalRequestDto request) {
        Customer customer = customerRepo.findById(request.getCustomerId())
                .orElseThrow(() -> new AlreadyExistsException(ErrorMessage.NOT_FOUND_EXCEPTION));

        Animal animal = animalMapper.toEntity(request, customer);
        Animal savedAnimal = animalRepo.save(animal);

        return animalMapper.toDto(savedAnimal);
    }

    @Override
    public AnimalResponseDto update(Long id, AnimalRequestDto request) {
        Animal animal = animalRepo.findById(id)
                .orElseThrow(() -> new AlreadyExistsException(ErrorMessage.NOT_FOUND_EXCEPTION));

        Customer customer = customerRepo.findById(request.getCustomerId())
                .orElseThrow(() -> new AlreadyExistsException(ErrorMessage.NOT_FOUND_EXCEPTION));

        Animal updatedAnimal = animalMapper.toEntity(request, customer);
        updatedAnimal.setId(id); // güncellenecek ID'yi set et

        Animal saved = animalRepo.save(updatedAnimal);
        return animalMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        Animal animal = animalRepo.findById(id)
                .orElseThrow(() -> new AlreadyExistsException(ErrorMessage.NOT_FOUND_EXCEPTION));
        animalRepo.delete(animal);
    }

    @Override
    public AnimalResponseDto getById(Long id) {
        Animal animal = animalRepo.findById(id)
                .orElseThrow(() -> new AlreadyExistsException(ErrorMessage.NOT_FOUND_EXCEPTION));
        return buildResponse(animal);
    }

    @Override
    public List<AnimalResponseDto> getAll() {
        return animalRepo.findAll()
                .stream()
                .map(this::buildResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnimalResponseDto> getByCustomerId(Long customerId) {
        return animalRepo.findByCustomerId(customerId)
                .stream()
                .map(this::buildResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnimalResponseDto> searchByName(String name) {
        return animalRepo.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::buildResponse)
                .collect(Collectors.toList());
    }

    private AnimalResponseDto buildResponse(Animal animal) {
        return AnimalResponseDto.builder()
                .id(animal.getId())
                .name(animal.getName())
                .species(animal.getSpecies())
                .breed(animal.getBreed())
                .gender(animal.getGender())
                .colour(animal.getColour())
                .dateOfBirth(animal.getDateOfBirth())
                .customerId(animal.getCustomer().getId())
                .customerName(animal.getCustomer().getName())
                .build();
    }

}

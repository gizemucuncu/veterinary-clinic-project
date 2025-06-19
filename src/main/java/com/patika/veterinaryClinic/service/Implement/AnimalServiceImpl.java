package com.patika.veterinaryClinic.service.Implement;

import com.patika.veterinaryClinic.dto.request.AnimalRequestDto;
import com.patika.veterinaryClinic.dto.response.AnimalResponseDto;
import com.patika.veterinaryClinic.entity.Animal;
import com.patika.veterinaryClinic.entity.Customer;
import com.patika.veterinaryClinic.exception.NotFoundException;
import com.patika.veterinaryClinic.exception.message.ErrorMessage;
import com.patika.veterinaryClinic.mapper.AnimalMapper;
import com.patika.veterinaryClinic.repository.AnimalRepo;
import com.patika.veterinaryClinic.repository.CustomerRepo;
import com.patika.veterinaryClinic.service.Interface.AnimalService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepo animalRepo;
    private final CustomerRepo customerRepo;
    private final AnimalMapper animalMapper;

    @Override
    public AnimalResponseDto save(AnimalRequestDto request) {
        // Kaydedilmek istenen hayvan için ilgili Customer kayıtlı kontrol edilir
        Customer customer = customerRepo.findById(request.getCustomerId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND_EXCEPTION));

        Animal animal = animalMapper.toEntity(request, customer);
        Animal savedAnimal = animalRepo.save(animal);

        return animalMapper.toDto(savedAnimal);
    }

    @Override
    public AnimalResponseDto update(Long id, AnimalRequestDto request) {
        Animal animal = animalRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessage.ANIMAL_NOT_FOUND_EXCEPTION, String.valueOf(id))));
        // Güncellenmek istenen hayvan için ilgili Customer kayıtlı kontrol edilir
        Customer customer = customerRepo.findById(request.getCustomerId())
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessage.CUSTOMER_NOT_FOUND_EXCEPTION, String.valueOf(request.getCustomerId()))));

        Animal updatedAnimal = animalMapper.toEntity(request, customer);
        updatedAnimal.setId(id); // güncellenecek ID set edilir

        Animal saved = animalRepo.save(updatedAnimal);
        return animalMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        Animal animal = animalRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessage.ANIMAL_NOT_FOUND_EXCEPTION, String.valueOf(id))));
        animalRepo.delete(animal);
    }

    @Override
    public AnimalResponseDto getById(Long id) {
        Animal animal = animalRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessage.ANIMAL_NOT_FOUND_EXCEPTION, String.valueOf(id))));
        return animalMapper.toDto(animal);
    }

    @Override
    public List<AnimalResponseDto> getAll() {
        return animalRepo.findAll()
                .stream()
                .map(animalMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnimalResponseDto> getByCustomerId(Long customerId) {
        //Customer ID'ye göre hayvanlar filtrelenir.
        List<AnimalResponseDto> animalByCustomer = animalRepo.findAll()
                .stream()
                .filter(animal -> animal.getCustomer().getId().equals(customerId))
                .map(animalMapper::toDto)
                .collect(Collectors.toList());
        if (animalByCustomer.isEmpty()) {
            throw new NotFoundException(String.format(ErrorMessage.ANIMAL_NOT_FOUND_WITH_CUSTOMER_ID_EXCEPTION, String.valueOf(customerId)));
        } else {
            return animalByCustomer;
        }

    }

    @Override
    public List<AnimalResponseDto> searchByName(String name) {
        //İsmine göre hayvanlar filtrelenir.
        List<AnimalResponseDto> animalByName = animalRepo.findByNameContainingIgnoreCase(name)
                .stream()
                .map(animalMapper::toDto)
                .collect(Collectors.toList());
        if (animalByName.isEmpty()) {
            throw new NotFoundException(ErrorMessage.ANIMAL_NAME_NOT_FOUND_EXCEPTION);
        } else {
            return animalByName;
        }
    }

    @Override
    public Animal getEntityById(Long id) {
        return animalRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessage.ANIMAL_NOT_FOUND_EXCEPTION, String.valueOf(id))));
    }

}

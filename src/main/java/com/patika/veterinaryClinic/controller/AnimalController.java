package com.patika.veterinaryClinic.controller;

import com.patika.veterinaryClinic.dto.request.AnimalRequestDto;
import com.patika.veterinaryClinic.dto.response.AnimalResponseDto;
import com.patika.veterinaryClinic.dto.response.VeterinaryResponse;
import com.patika.veterinaryClinic.service.AnimalService;
import com.patika.veterinaryClinic.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animal")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;
    // private CustomerService customerService;
    // Vaccine e bağlanmalı birde

    @PostMapping
    public ResponseEntity<AnimalResponseDto> saveAnimal(@Valid @RequestBody AnimalRequestDto request) {
        return new ResponseEntity<>(animalService.save(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(animalService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponseDto> updateAnimal(@PathVariable Long id,
                                                          @Valid @RequestBody AnimalRequestDto request) {
        return ResponseEntity.ok(animalService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnimal(@PathVariable Long id) {
        animalService.delete(id);

        VeterinaryResponse response = new VeterinaryResponse();
        response.setMessage(id + " numaralı id'ye sahip hayvan kaydı başarıyla silindi.");
        response.setSuccess(true);
        return ResponseEntity.ok("Hayvan silindi: ID " + id);
        //return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AnimalResponseDto>> getAll() {
        return ResponseEntity.ok(animalService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<AnimalResponseDto>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(animalService.searchByName(name));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AnimalResponseDto>> getByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(animalService.getByCustomerId(customerId));
    }


}

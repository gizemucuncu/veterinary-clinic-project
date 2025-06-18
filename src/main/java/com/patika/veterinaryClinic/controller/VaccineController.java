package com.patika.veterinaryClinic.controller;

import com.patika.veterinaryClinic.dto.request.VaccineRequestDto;
import com.patika.veterinaryClinic.dto.response.VaccineResponseDto;
import com.patika.veterinaryClinic.dto.response.VeterinaryResponse;
import com.patika.veterinaryClinic.mapper.VaccineMapper;
import com.patika.veterinaryClinic.service.VaccineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/api/vaccine")
@RequiredArgsConstructor
public class VaccineController {
    private final VaccineService vaccineService;

    @PostMapping
    public ResponseEntity<VaccineResponseDto> create(@Valid @RequestBody VaccineRequestDto request) {
        return ResponseEntity.ok(vaccineService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VaccineResponseDto> update(@PathVariable Long id,
                                                     @Valid @RequestBody VaccineRequestDto request) {
        return ResponseEntity.ok(vaccineService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VeterinaryResponse> delete(@PathVariable Long id) {
        vaccineService.delete(id);

        VeterinaryResponse response = new VeterinaryResponse();
        response.setMessage(id + " numaralı id'ye sahip aşı kaydı başarıyla silindi.");
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaccineResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vaccineService.getById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<VaccineResponseDto>> getAll() {
        return ResponseEntity.ok(vaccineService.getAll());
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<VaccineResponseDto>> getByAnimalId(@PathVariable Long animalId) {
        return ResponseEntity.ok(vaccineService.getAllByAnimalId(animalId));
    }

    @GetMapping("/protection")
    public ResponseEntity<List<VaccineResponseDto>> getByProtectionDateRange(@Valid @RequestParam LocalDate startDate,
                                                                             @Valid @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(vaccineService.getByProtectionDateRange(startDate, endDate));
    }
}

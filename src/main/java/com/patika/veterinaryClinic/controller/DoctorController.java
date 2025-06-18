package com.patika.veterinaryClinic.controller;


import com.patika.veterinaryClinic.dto.request.DoctorRequestDto;
import com.patika.veterinaryClinic.dto.response.DoctorListResponseDto;
import com.patika.veterinaryClinic.dto.response.DoctorResponseDto;
import com.patika.veterinaryClinic.dto.response.VeterinaryResponse;
import com.patika.veterinaryClinic.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<DoctorResponseDto> create(@Valid @RequestBody DoctorRequestDto requestDto) {
        DoctorResponseDto saved = doctorService.save(requestDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> update(@PathVariable Long id,
                                                          @Valid @RequestBody DoctorRequestDto requestDto) {
        DoctorResponseDto updated = doctorService.update(id, requestDto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> getDoctor(@PathVariable Long id) {
        DoctorResponseDto response = doctorService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<DoctorListResponseDto>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAll());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<VeterinaryResponse> delete(@PathVariable Long id) {
        doctorService.delete(id);

        VeterinaryResponse response = new VeterinaryResponse();
        response.setMessage(id + " numaralı id'ye sahip doktor kaydı başarıyla silindi.");
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }
}

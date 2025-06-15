package com.patika.veterinaryClinic.controller;

import com.patika.veterinaryClinic.dto.request.AvailableDateRequestDto;
import com.patika.veterinaryClinic.dto.response.AvailableDateResponseDto;
import com.patika.veterinaryClinic.dto.response.VeterinaryResponse;
import com.patika.veterinaryClinic.entity.Doctor;
import com.patika.veterinaryClinic.exception.AlreadyExistsException;
import com.patika.veterinaryClinic.exception.message.ErrorMessage;
import com.patika.veterinaryClinic.repository.DoctorRepo;
import com.patika.veterinaryClinic.service.AvailableDateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/available-date")
@RequiredArgsConstructor
public class AvailableDateController {
    private final AvailableDateService availableDateService;
    private final DoctorRepo doctorRepo;

    @PostMapping
    public ResponseEntity<AvailableDateResponseDto> save(@Valid @RequestBody AvailableDateRequestDto request) {
        return ResponseEntity.ok(availableDateService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvailableDateResponseDto> update(@PathVariable Long id,
                                                           @Valid @RequestBody AvailableDateRequestDto request) {
        return ResponseEntity.ok(availableDateService.update(id, request));
    }


    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AvailableDateResponseDto>> getByDoctorId(@PathVariable Long doctorId) {
        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new AlreadyExistsException(String.format(ErrorMessage.NOT_FOUND_EXCEPTION, String.valueOf(doctorId))));
        return ResponseEntity.ok(availableDateService.getAllByDoctorId(doctorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvailableDateResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(availableDateService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VeterinaryResponse> delete(@PathVariable Long id) {
        availableDateService.delete(id);

        VeterinaryResponse response = new VeterinaryResponse();
        response.setMessage(id + " numaralı id'ye sahip 'müsait tarih' kaydı başarıyla silindi.");
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }
}

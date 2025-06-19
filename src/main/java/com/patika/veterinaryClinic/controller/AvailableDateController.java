package com.patika.veterinaryClinic.controller;

import com.patika.veterinaryClinic.dto.request.AvailableDateRequestDto;
import com.patika.veterinaryClinic.dto.response.AvailableDateResponseDto;
import com.patika.veterinaryClinic.dto.response.VeterinaryResponse;
import com.patika.veterinaryClinic.service.Interface.AvailableDateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/available-date")
@RequiredArgsConstructor
public class AvailableDateController {
    private final AvailableDateService availableDateService;

    @PostMapping
    public ResponseEntity<AvailableDateResponseDto> create(@Valid @RequestBody AvailableDateRequestDto request) {
        return ResponseEntity.ok(availableDateService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvailableDateResponseDto> update(@PathVariable Long id,
                                                           @Valid @RequestBody AvailableDateRequestDto request) {
        return ResponseEntity.ok(availableDateService.update(id, request));
    }


    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AvailableDateResponseDto>> getByDoctorId(@PathVariable Long doctorId) {
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

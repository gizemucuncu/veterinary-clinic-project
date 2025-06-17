package com.patika.veterinaryClinic.controller;

import com.patika.veterinaryClinic.dto.request.AppointmentRequestDto;
import com.patika.veterinaryClinic.dto.response.AppointmentResponseDto;
import com.patika.veterinaryClinic.entity.Appointment;
import com.patika.veterinaryClinic.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDto> create(@Valid @RequestBody AppointmentRequestDto request) {
        AppointmentResponseDto saveAppointment = appointmentService.save(request);
        return new ResponseEntity<>(saveAppointment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> update(@PathVariable Long id,
                                                         @Valid @RequestBody AppointmentRequestDto request) {
        AppointmentResponseDto updateAppointment = appointmentService.update(id, request);
        return new ResponseEntity<>(updateAppointment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<AppointmentResponseDto>> getAll() {
        return ResponseEntity.ok(appointmentService.getAll());
    }

    @GetMapping("/doctor")
    public ResponseEntity<List<AppointmentResponseDto>> getByDoctorIdAndDateRange(
            @Valid @RequestParam Long doctorId,
            @Valid @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @Valid @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(appointmentService.getByDoctorIdAndDateRange(doctorId, start, end));

    }

    @GetMapping("/animal")
    public ResponseEntity<List<AppointmentResponseDto>> getByAnimalIdAndDateRange(
            @Valid @RequestParam Long animalId,
            @Valid @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @Valid @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        return ResponseEntity.ok(appointmentService.getByAnimalIdAndDateRange(animalId, start, end));
    }
}

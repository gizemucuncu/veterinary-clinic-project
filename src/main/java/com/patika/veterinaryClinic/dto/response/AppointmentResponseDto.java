package com.patika.veterinaryClinic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentResponseDto {

    private Long id;
    private LocalDateTime appointmentDate;

    private Long doctorId;
    private String doctorName;

    private Long animalId;
    private String animalName;
}

package com.patika.veterinaryClinic.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequestDto {
    @NotNull(message = "Tarih ve saat bilgisi boş alamaz!")
    private LocalDateTime appointmentDate;

    @NotNull(message = "Doctor ID bilgisi boş alamaz!")
    private Long doctorId;

    @NotNull(message = "Animal ID bilgisi boş alamaz!")
    private Long animalId;
}

package com.patika.veterinaryClinic.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvailableDateRequestDto {
    @NotNull(message = "Tarih bilgisi boş olamaz!")
    private LocalDate availableDate;
    @NotNull(message = "Doctor ID bilgisi boş olamaz!")
    private Long doctorId;

}

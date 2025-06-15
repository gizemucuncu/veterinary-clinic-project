package com.patika.veterinaryClinic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDateResponseDto {
    private Long id;
    private LocalDate availableDate;
    private Long doctorId;
    private String doctorName;
}

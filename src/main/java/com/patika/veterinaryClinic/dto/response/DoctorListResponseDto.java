package com.patika.veterinaryClinic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorListResponseDto {
    private Long id;
    private String name;
    private String city;
}

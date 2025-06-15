package com.patika.veterinaryClinic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerListResponseDto {
    private Long id;
    private String name;
    private String phone;
    private String mail;
}

package com.patika.veterinaryClinic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDto {
    private Long id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
}

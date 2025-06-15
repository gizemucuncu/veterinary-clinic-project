package com.patika.veterinaryClinic.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {
    @NotBlank(message = "İsim bilgisi boş olamaz!")
    private String name;

    @NotBlank(message = "Phone bilgisi boş olamaz!")
    private String phone;

    @NotBlank(message = "Mail bilgisi boş olamaz!")
    private String mail;
    private String address;
    private String city;
}

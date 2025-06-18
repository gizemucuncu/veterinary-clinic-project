package com.patika.veterinaryClinic.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequestDto {

    @NotBlank(message = "Doktor adı boş olamaz.")
    private String name;

    @NotBlank(message = "Telefon numarası boş olamaz.")
    private String phone;

    @NotBlank(message = "Email boş olamaz.")
    @Email(message = "Geçerli bir email giriniz.")
    private String mail;

    @NotBlank(message = "Adres bilgisi boş olamaz.")
    private String address;

    @NotBlank(message = "Şehir bilgisi boş olamaz.")
    private String city;

}

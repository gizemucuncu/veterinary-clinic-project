package com.patika.veterinaryClinic.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalRequestDto {
    @NotBlank(message = "İsim bilgisi boş olamaz!")
    private String name;
    @NotBlank
    private String species;
    @NotBlank
    private String breed;
    @NotBlank
    private String gender;
    private String colour;
    @NotNull(message = "Doğum tarihi bilgisi boş olamaz!")
    private LocalDate dateOfBirth;
    @NotNull(message = "Customer ID zorunludur.")
    private Long customerId;
}

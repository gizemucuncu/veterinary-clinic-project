package com.patika.veterinaryClinic.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeterinaryResponse {
    private String message;
    private Boolean success;
}

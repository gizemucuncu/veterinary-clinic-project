package com.patika.veterinaryClinic.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineRequestDto {
    @NotBlank(message = "Aşı bilgisi boş olamaz!")
    private String name;

    @NotBlank(message = "Aşı kod bilgisi boş olamaz!")
    private String code;

    @NotNull(message = "Koruma Başlangıç tarihi bilgisi boş olamaz!")
    @FutureOrPresent(message = "Koruma Başlangıç tarihi bugünden önce olamaz.")
    private LocalDate protectionStartDate;

    @NotNull(message = "Koruma Bitiş tarihi bilgisi boş olamaz!")
    private LocalDate protectionFinishDate;

    @NotNull(message = "Animal ID boş olamaz.")
    private Long animalId;

    @AssertTrue(message = "Koruma bitiş tarihi, başlangıç tarihinden sonra olmalıdır!")
    public boolean isProtectionFinishDateValid() {
        if (protectionStartDate == null || protectionFinishDate == null) {
            return true;
        }
        return !protectionFinishDate.isBefore(protectionStartDate);
    }
}

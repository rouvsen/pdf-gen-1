package com.rouvsen.pdfgen1.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class StatementRequestDto {

    @NotBlank
    String customerId;

    @Default
    LocalDate beginDate = LocalDate.now().minusDays(10);;

    @Default
    LocalDate endDate =  LocalDate.now();
}

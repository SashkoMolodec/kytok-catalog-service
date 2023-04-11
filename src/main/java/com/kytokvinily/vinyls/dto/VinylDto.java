package com.kytokvinily.vinyls.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class VinylDto {

    @NotBlank
    private String title;
    @Min(1900)
    private int year;
    @NotBlank
    private String author;
}

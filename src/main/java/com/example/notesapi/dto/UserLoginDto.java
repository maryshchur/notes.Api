package com.example.notesapi.dto;

import com.example.notesapi.constants.ValidationErrorConstants;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class UserLoginDto {
    @NotBlank(message = ValidationErrorConstants.EMPTY_EMAIL)
    private String email;
    @NotBlank(message = ValidationErrorConstants.EMPTY_PASSWORD)
    private String password;
}

package com.example.notesapi.dto;

import com.example.notesapi.constants.ValidationErrorConstants;
import com.example.notesapi.util.EmailExist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    @NotBlank(message = ValidationErrorConstants.EMPTY_FIRSTNAME)
    @Size(min = 3, max = 20)
    public String firstName;

    @NotBlank(message = ValidationErrorConstants.EMPTY_LASTNAME)
    @Size(min = 3, max = 30)
    public String lastName;

    @NotBlank(message = ValidationErrorConstants.EMPTY_EMAIL)
    @Email(message = ValidationErrorConstants.INVALID_EMAIL)
    @EmailExist
    public String email;

    @Size(min = 6, max = 30)
    @NotBlank(message = ValidationErrorConstants.EMPTY_PASSWORD)
    private String password;

}

package com.example.notesapi.dto;

import com.example.notesapi.util.EmailExist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    @NotBlank(message = "fdfvdvgfdsv")
//            message = ValidationErrorConstants.EMPTY_FIRSTNAME)
    @Size(min = 3, max = 20)
    public String firstName;

    @NotBlank(message = "fdfvdvgfdsv")
//            (message = ValidationErrorConstants.EMPTY_LASTNAME)
    @Size(min = 3, max = 30)
    public String lastName;

    @NotBlank
//            (message = ValidationErrorConstants.EMPTY_EMAIL)
    @Email
//            (message = ValidationErrorConstants.INVALID_EMAIL)
    @EmailExist
    public String email;

    @Size(min = 6, max = 30)
    @NotBlank
    private String password;

}
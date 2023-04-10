package com.example.notesapi.util;

import com.example.notesapi.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class EmailValidator implements ConstraintValidator<EmailExist, String> {

    private final UserRepository userRepository;


    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userRepository.findByEmail(email.trim().toLowerCase()).isPresent();
    }
}

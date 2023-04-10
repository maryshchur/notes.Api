package com.example.notesapi.security;

import com.example.notesapi.dto.UserLoginDto;
import com.example.notesapi.entity.User;
import com.example.notesapi.repository.UserRepository;
import com.example.notesapi.security.config.SecurityConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthenticationService {

    private final TokenManagementService tokenManagementService;
    private final UserRepository userRepository;
    private final SecurityConfiguration webSecurityConfig;

    public String loginUser(UserLoginDto loginUser) {

        Optional<User> user = userRepository.findByEmail(loginUser.getEmail());
        PasswordEncoder passwordEncoder = webSecurityConfig.passwordEncoder();
        if (user.isPresent() && passwordEncoder.matches(loginUser.getPassword(), user.get().getPassword())) {
            return tokenManagementService.generateTokenPair(loginUser.getEmail());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "BAD CREDENTIAL");
        }

    }
}

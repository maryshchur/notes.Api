package com.example.notesapi.security;

import com.example.notesapi.dto.UserLoginDto;
import com.example.notesapi.entity.User;
import com.example.notesapi.repository.UserRepository;
import com.example.notesapi.security.config.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationService {

    private TokenManagementService tokenManagementService;
    private UserRepository userRepository;
    private SecurityConfiguration webSecurityConfig;

    @Autowired
    public AuthenticationService(TokenManagementService tokenManagementService,
                                 UserRepository userRepository,
                                 SecurityConfiguration webSecurityConfig) {
        this.tokenManagementService = tokenManagementService;
        this.userRepository = userRepository;
        this.webSecurityConfig = webSecurityConfig;
    }

    public String loginUser(UserLoginDto loginUser) {

        Optional<User> user = userRepository.findByEmail(loginUser.getEmail());
        PasswordEncoder passwordEncoder = webSecurityConfig.passwordEncoder();
        if (user.isPresent() && passwordEncoder.matches(loginUser.getPassword(), user.get().getPassword())) {
            return tokenManagementService.generateTokenPair(loginUser.getEmail());
        } else {
            throw new RuntimeException("BAD CREDENTIAL");
//                    BadCredentialException("BAD CREDENTIAL");
        }

    }
}

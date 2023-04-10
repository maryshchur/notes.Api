package com.example.notesapi.service.impl;

import com.example.notesapi.dto.UserDto;
import com.example.notesapi.entity.User;
import com.example.notesapi.exception.CustomException;
import com.example.notesapi.repository.UserRepository;
import com.example.notesapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    @Override
    public void save(UserDto user) {
        User entity = modelMapper.map(user, User.class);
        System.out.println("entity +"+entity);
        System.out.println(passwordEncoder);
        entity.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepository.save(entity) == null) {
            LOGGER.info("error creating user with email - " +  user.getEmail());
            throw new CustomException(
                    String.format("User with %s email was not saved due to internal error",
                            user.getEmail()));
        }
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).
                orElseThrow(() ->
                        new CustomException(String.format("User with %s email was not found", email)));
    }
}

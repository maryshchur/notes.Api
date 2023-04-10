package com.example.notesapi.controller;


import com.example.notesapi.dto.UserDto;
import com.example.notesapi.dto.UserLoginDto;
import com.example.notesapi.security.AuthenticationService;
import com.example.notesapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping
    public void create(@RequestBody @Valid UserDto user) {
        System.out.println("UserDto" + user);
        userService.save(user);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginDto user) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.loginUser(user));
    }
}

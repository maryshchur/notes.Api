package com.example.notesapi.controller;


import com.example.notesapi.constants.HttpStatuses;
import com.example.notesapi.dto.UserDto;
import com.example.notesapi.dto.UserLoginDto;
import com.example.notesapi.security.AuthenticationService;
import com.example.notesapi.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = HttpStatuses.CREATED),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
    })
    @PostMapping
    public void create(@RequestBody @Valid UserDto user) {
        userService.save(user);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200,message = HttpStatuses.OK),
            @ApiResponse(code = 401 ,message = HttpStatuses.UNAUTHORIZED)
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginDto user) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.loginUser(user));
    }
}

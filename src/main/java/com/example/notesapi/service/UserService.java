package com.example.notesapi.service;

import com.example.notesapi.dto.UserDto;
import com.example.notesapi.entity.User;

public interface UserService {
    void save(UserDto note);
    User findByEmail(String email);

}

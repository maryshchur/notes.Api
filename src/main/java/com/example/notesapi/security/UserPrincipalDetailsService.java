package com.example.notesapi.security;

import com.example.notesapi.entity.User;
import com.example.notesapi.exception.CustomException;
import com.example.notesapi.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = (User) userRepository.findByEmail(username)
                .orElseThrow(
                        () -> new CustomException("User does not exist"));
//                                new NotFoundException("User does not exist"));
        return UserPrincipal.create(user);
    }
}

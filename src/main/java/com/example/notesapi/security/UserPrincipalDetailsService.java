package com.example.notesapi.security;

import com.example.notesapi.entity.User;
import com.example.notesapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserPrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "User does not exist"));
        return UserPrincipal.create(user);
    }
}

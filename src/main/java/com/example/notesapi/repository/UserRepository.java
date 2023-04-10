package com.example.notesapi.repository;

import com.example.notesapi.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User save(User u);

    Optional<User> findByEmail(String email);
}

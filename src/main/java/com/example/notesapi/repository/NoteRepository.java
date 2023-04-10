package com.example.notesapi.repository;

import com.example.notesapi.entity.Note;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    Note save(Note entity);

    void deleteById(String id);

    boolean existsById(String id);

    List<Note> findAll(Sort sort);

}

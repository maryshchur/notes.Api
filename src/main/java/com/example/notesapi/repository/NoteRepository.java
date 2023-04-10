package com.example.notesapi.repository;

import com.example.notesapi.entity.Note;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

//    @Query("{name:'?0'}")
//    Note findItemByName(String name);

//    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
//    List<Note> findAll(String category);


     Note save(Note entity);

//     Optional<Note> update(Note entity);

     void deleteById(String id);

     boolean existsById(String id);

//    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
//    @Query(sort="createdDate")
     List<Note> findAll(Sort sort);

     List<Note> findAllByOrderByCreatedDateAsc();

//    @Override
//    List<Note> find
}

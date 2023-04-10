package com.example.notesapi.service.impl;

import com.example.notesapi.dto.NoteDto;
import com.example.notesapi.entity.Note;
import com.example.notesapi.entity.User;
import com.example.notesapi.exception.CustomException;
import com.example.notesapi.repository.NoteRepository;
import com.example.notesapi.service.NoteService;
import com.example.notesapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoteServiceImpl.class);

    private static final String SORTING_FIELD = "createdDate";

    private final NoteRepository noteRepository;

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public void save(String note) {
        //TODO logger and catch exception this shit into try catch
        Note n = noteRepository.save(Note.builder().content(note).build());
        System.out.println(n);
        System.out.println("save");
    }

    @Override
    public void delete(String id) {
        if (noteRepository.existsById(id)) {
            //TODO logger and catch exception this shit into try catch
            noteRepository.deleteById(id);
        } else {
            LOGGER.info("an error occurred during deleting note as note with id - " + id + " does not exist");
            throw new CustomException("Note with " + id + " does not exist");
        }
    }

    @Override
    public List<NoteDto> getAllNotes(String sortDirection) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("DESC") ?
                Sort.Direction.DESC : Sort.Direction.ASC;
        return noteRepository.findAll(Sort.by(direction, SORTING_FIELD)).
                stream().map(x -> modelMapper.map(x, NoteDto.class)).
                collect(Collectors.toList());
    }

    private Note findById(String id){
        return noteRepository.findById(id).orElseThrow(() ->
                new CustomException(String.format("Note with %s id was not found",id)));
    }

    @Override
    public void like(String userEmail, String noteId) {
        User user = userService.findByEmail(userEmail);
        Note note = findById(noteId);
        Set<User> allLikesFromUsers = note.getLikes();
        if (allLikesFromUsers.contains(user)) {
            allLikesFromUsers.remove(user);
        } else {
            allLikesFromUsers.add(user);
        }
        noteRepository.save(note);


    }
}

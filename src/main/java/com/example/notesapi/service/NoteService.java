package com.example.notesapi.service;

import com.example.notesapi.dto.NoteDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NoteService {
    void save(String note);

    void delete(String id);

    void like( String userEmail,String noteId);

    List<NoteDto> getAllNotes(String sortDirection);
}

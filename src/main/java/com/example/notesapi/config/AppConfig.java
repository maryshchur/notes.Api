package com.example.notesapi.config;

import com.example.notesapi.dto.NoteDto;
import com.example.notesapi.entity.Note;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Note.class, NoteDto.class).setPreConverter(context ->
        {
            NoteDto noteDto = context.getDestination();
            noteDto.setAmountOflikes(context.getSource().getLikes().size());
            return noteDto;
        });
        return modelMapper;
    }

}

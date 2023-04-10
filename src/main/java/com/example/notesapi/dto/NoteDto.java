package com.example.notesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {

    private String id;

    private int amountOflikes;

    private Timestamp createdDate;

    public String content;
}

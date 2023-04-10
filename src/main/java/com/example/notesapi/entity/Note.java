package com.example.notesapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Note {
    @Id
    private String id;


    @Indexed
    @CreatedDate
    private Date createdDate;

    @NotBlank
    public String content;

    @Field("likes")
    @DocumentReference(lazy = true)
//            (lookup = "{ 'acronym' : ?#{#target} }")
    private Set<User> likes;
}

package com.example.notesapi.entity;

//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //    @MongoId(FieldType.OBJECT_ID)
    @Id
    private String id;

    @NotBlank
    @Size(max = 40)
    private String firstName;

    @NotBlank
    @Size(max = 40)
    private String lastName;

    @NotBlank
    @Size(max = 40)
    @Indexed(unique = true)
    private String email;

    @NotBlank
    private String password;

//    @Field("likedNotes")
//    @DocumentReference(lookup = "{ 'acronym' : ?#{#target} }",lazy = true)
//    private Set<Note> likedNotes;


}

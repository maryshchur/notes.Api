package com.example.notesapi.entity;

//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
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

//import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Set;

//@Entity
//@Table(name = "users")
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Accessors(chain = true)
public class Note {
    //    @MongoId(FieldType.OBJECT_ID)
    @Id
    private String id;


    @Indexed
    @CreatedDate
    private Date createdDate;

    @NotBlank
    public String content;

    @Field("likes")
    @DocumentReference()
//            (lookup = "{ 'acronym' : ?#{#target} }")
    private Set<User> likes;
}

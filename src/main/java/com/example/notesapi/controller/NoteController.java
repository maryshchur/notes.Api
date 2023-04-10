package com.example.notesapi.controller;


import com.example.notesapi.dto.NoteDto;
//import com.example.notesapi.security.UserPrincipal;
import com.example.notesapi.service.NoteService;
//import jakarta.validation.Valid;
//import jakarta.validation.Valid;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
//import springfox.documentation.annotations.ApiIgnore;

//import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public void create(@RequestBody @Valid @NotBlank @Size(min = 15) @RequestParam String field ){
        noteService.save(field);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //глянути який http статус вертає при успішному видаленні, якщ що то нехай метод вертає
    // HttpStatus.OK і чи потрібно з responceBody вкртати HttpStatus
//        @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "deleted"),
////                    String  HttpStatus.CREATED.toString()),
//            @ApiResponse(code = 400, message = "BAD_REQUEST")
////            HttpStatus.BAD_REQUEST
//    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        noteService.delete(id);
//        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<NoteDto>> getAllNotes(@RequestParam String sortDirection) {
        return ResponseEntity.status(HttpStatus.OK).body(noteService.getAllNotes(sortDirection));
    }

//    /?order=course_asc

//    @ApiOperation(value = "", authorizations = {@Authorization(value = "JWT")})
@SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{id}/like")
    public void likeNote(
//            @ApiIgnore @AuthenticationPrincipal UserPrincipal
            String principal,
                         @PathVariable String id) {
        noteService.like(
                principal ,
//                .getUsername(),
                id);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

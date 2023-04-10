package com.example.notesapi.controller;


import com.example.notesapi.constants.HttpStatuses;
import com.example.notesapi.dto.NoteDto;
import com.example.notesapi.security.UserPrincipal;
import com.example.notesapi.service.NoteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;


    @ApiResponses(value = {
            @ApiResponse(code = 201, message = HttpStatuses.CREATED),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
    })
    @PostMapping
    public void create( @Valid @NotBlank @Size(min = 15) @RequestParam String field) {
        noteService.save(field);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        noteService.delete(id);
//        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
    })
    @GetMapping
    public ResponseEntity<List<NoteDto>> getAllNotes(@RequestParam String sortDirection) {
        return ResponseEntity.status(HttpStatus.OK).body(noteService.getAllNotes(sortDirection));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
            @ApiResponse(code = 401, message = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
    })
    @ApiOperation(value = "", authorizations = {@Authorization(value = "JWT")})
    @PutMapping("/{id}/like")
    public void likeNote(@ApiIgnore @AuthenticationPrincipal UserPrincipal principal,
                         @PathVariable String id) {
        noteService.like(principal.getUsername(), id);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

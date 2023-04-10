package com.example.notesapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

//@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleRuntimeException
            (RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new HashMap<String, String>() {{
                    put("message", exception.getMessage());
                }});
    }


//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @Override
//    public ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException exception,
//            HttpHeaders headers,
//            HttpStatus status,
//            WebRequest request) {
//        List<ValidationExceptionDto> collect =
//                exception.getBindingResult().getFieldErrors().stream()
//                        .map(ValidationExceptionDto::new)
//                        .collect(Collectors.toList());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(collect);
//    }

}

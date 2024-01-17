package com.example.movie.exception;

import com.example.movie.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleException(BadRequestException e) {
        // Xử lý và trả về response phù hợp, có thể là một mã lỗi và thông báo.
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleException(ResourceNotFoundException e) {
        // Xử lý và trả về response phù hợp, có thể là một mã lỗi và thông báo.
        return null;
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Xử lý và trả về response phù hợp, có thể là một mã lỗi và thông báo.
        return null;
    }

}

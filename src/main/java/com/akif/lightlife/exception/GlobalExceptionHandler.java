package com.akif.lightlife.exception;

import com.akif.lightlife.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            NotFoundException ex,
            HttpServletRequest request
    ) {
        ErrorResponse body = ErrorResponse.builder()
                .zaman(LocalDateTime.now())
                .mesaj(ex.getMessage())
                .kod("NOT_FOUND")
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        String msg = ex.getBindingResult()
                       .getFieldErrors()
                       .stream()
                       .findFirst()
                       .map(e -> e.getField() + " " + e.getDefaultMessage())
                       .orElse("Geçersiz istek");

        ErrorResponse body = ErrorResponse.builder()
                .zaman(LocalDateTime.now())
                .mesaj(msg)
                .kod("VALIDATION_ERROR")
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(
            Exception ex,
            HttpServletRequest request
    ) {
        ex.printStackTrace(); // Log için

        ErrorResponse body = ErrorResponse.builder()
                .zaman(LocalDateTime.now())
                .mesaj("Beklenmeyen bir hata oluştu")
                .kod("GENERIC_ERROR")
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}

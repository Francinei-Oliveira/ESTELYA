package com.estelya.common.api;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> handleValidation(
            MethodArgumentNotValidException exception,
            HttpServletRequest request) {

        Map<String, String> fields = new LinkedHashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        fields.put(
                                error.getField(),
                                error.getDefaultMessage()));

        return response(
                HttpStatus.BAD_REQUEST,
                "Dados invalidos.",
                request,
                fields
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<ApiError> handleBadCredentials(
            BadCredentialsException exception,
            HttpServletRequest request) {

        return response(
                HttpStatus.UNAUTHORIZED,
                "E-mail ou senha invalidos.",
                request,
                Map.of()
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    ResponseEntity<ApiError> handleAccessDenied(
            AccessDeniedException exception,
            HttpServletRequest request) {

        return response(
                HttpStatus.FORBIDDEN,
                "Acesso negado.",
                request,
                Map.of()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<ApiError> handleBusinessRule(
            IllegalArgumentException exception,
            HttpServletRequest request) {

        return response(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                request,
                Map.of()
        );
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiError> handleUnexpected(
            Exception exception,
            HttpServletRequest request) {

        return response(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno inesperado.",
                request,
                Map.of()
        );
    }

    private ResponseEntity<ApiError> response(
            HttpStatus status,
            String message,
            HttpServletRequest request,
            Map<String, String> fields) {

        return ResponseEntity.status(status).body(
                new ApiError(
                        Instant.now(),
                        status.value(),
                        status.getReasonPhrase(),
                        message,
                        request.getRequestURI(),
                        fields
                )
        );
    }
}
package com.lewis.spring_boot_practice.exception;

import com.lewis.spring_boot_practice.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidation(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new LinkedHashMap<>();

        exception.getBindingResult().getFieldErrors()
                .forEach((error) -> errors.putIfAbsent(
                        error.getField(),
                        error.getDefaultMessage()
                ));

        return new ApiError(
                "VALIDATION_FAILED",
                "Please check your input",
                errors
        );
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiError> handleResponseStatus(ResponseStatusException exception) {

        String code = exception.getStatusCode().value() == 404
                ? "TODO_NOT_FOUND"
                : "REQUEST_FAILED";

        ApiError error = new ApiError(
                code,
                exception.getReason() != null
                    ? exception.getReason()
                    : "Request failed",
                Map.of()
        );

        return ResponseEntity
                .status(exception.getStatusCode())
                .body(error);
    }
}

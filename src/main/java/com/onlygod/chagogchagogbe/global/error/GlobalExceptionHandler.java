package com.onlygod.chagogchagogbe.global.error;

import com.onlygod.chagogchagogbe.global.error.exception.CustomException;
import com.onlygod.chagogchagogbe.global.error.exception.ErrorCode;
import com.onlygod.chagogchagogbe.global.error.response.ErrorResponse;
import com.onlygod.chagogchagogbe.global.error.response.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse response = new ErrorResponse(errorCode.getStatus(), errorCode.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ValidationErrorResponse handleValidException(MethodArgumentNotValidException e) {
        Map<String, String> filedErrors = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            filedErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ValidationErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.name())
                .error(filedErrors)
                .build();
    }
}

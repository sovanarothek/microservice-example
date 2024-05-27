package com.example.common.base.exception;

import com.example.common.base.util.ApiResult;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResult<Void>> handleException(Exception ex) {
        ApiResult<Void> errorResponse = new ApiResult<>(HttpStatus.CONFLICT.value(), ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResult<Void>> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ApiResult<Void> errorResponse = new ApiResult<>(HttpStatus.CONFLICT.value(), ex.getMessage());
        /* return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT); */
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(MissingFieldException.class)
    public ResponseEntity<ApiResult<Void>> handleMissingFieldException(MissingFieldException ex) {
        ApiResult<Void> errorResponse = new ApiResult<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResult<Void>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ApiResult<Void> errorResponse = new ApiResult<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResult<Void>> handleUserNotFoundException(UserNotFoundException ex) {
        ApiResult<Void> errorResponse = new ApiResult<>(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiResult<Void>> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        ApiResult<Void> errorResponse = new ApiResult<>(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public ResponseEntity<ApiResult<Void>> handleInvalidJwtAuthenticationException(InvalidJwtAuthenticationException ex) {
        ApiResult<Void> errorResponse = new ApiResult<>(HttpStatus.FORBIDDEN.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResult<Void>> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ApiResult<Void> errorResponse = new ApiResult<>(HttpStatus.BAD_REQUEST.value(), errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(SessionExpiredException.class)
    public ResponseEntity<ApiResult<Void>> handleSessionExpiredException(SessionExpiredException ex) {
        ApiResult<Void> errorResponse = new ApiResult<>(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResult<Void>> handle(ExpiredJwtException ex) {
        ApiResult<Void> errorResponse = new ApiResult<>(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }
}



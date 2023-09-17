package com.example.demo.infra.config.exceptions;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.demo.core.domain._shared.exceptions.AlreadyExistsException;
import com.example.demo.core.domain._shared.exceptions.BadRequestException;
import com.example.demo.core.domain._shared.exceptions.InvalidPropException;
import com.example.demo.core.domain._shared.exceptions.InvalidTypeException;
import com.example.demo.core.domain._shared.exceptions.NotFoundException;

@RestControllerAdvice
public class AppExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(AppExceptionHandler.class);

  private ResponseEntity<ExceptionResponse> handler(Exception ex, HttpStatus status, String message, List<String> errors) {
    LOGGER.error("{}: {}", ex.getClass().getName(), ex.getMessage());

    ExceptionResponse response = ExceptionResponse.builder()
        .code(status.value())
        .status(status.getReasonPhrase())
        .message(message)
        .timestamp(new Date().toString())
        .errors(errors)
        .build();

    return ResponseEntity.status(status).body(response);
  }

  private ResponseEntity<ExceptionResponse> handler(Exception ex, HttpStatus status, String message) {
    return handler(ex, status, message, List.of());
  }

  private ResponseEntity<ExceptionResponse> handler(Exception ex, HttpStatus status, List<String> errors) {
    return handler(ex, status, ex.getMessage(), errors);
  }

  private ResponseEntity<ExceptionResponse> handler(Exception ex, HttpStatus status) {
    return handler(ex, status, ex.getMessage(), List.of());
  }
  
   @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> handleBadRequestException(Exception ex) {
    return handler(ex, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(InvalidTypeException.class)
  public ResponseEntity<ExceptionResponse> handleBadRequestException(InvalidTypeException ex) {
    return handler(ex, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException ex) {
    return handler(ex, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ExceptionResponse> handleResourceNotFound(NotFoundException ex) {
    return handler(ex, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
    return handler(ex, HttpStatus.NOT_ACCEPTABLE);
  }

  @ExceptionHandler(InvalidPropException.class)
  public ResponseEntity<ExceptionResponse> handleInvalidPropException(InvalidPropException ex) {
    return handler(ex, HttpStatus.NOT_ACCEPTABLE, ex.getErrors());
  }


  @ExceptionHandler(AlreadyExistsException.class)
  public ResponseEntity<ExceptionResponse> handleResourceAlreadyExistsException(AlreadyExistsException ex) {
    return handler(ex, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ExceptionResponse> handleConstraintViolationException(DataIntegrityViolationException ex) {
    return handler(ex, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionResponse> handleInvalidMethodArguments(MethodArgumentNotValidException ex) {
    List<String> errorMessages = ex.getBindingResult().getFieldErrors()
        .stream()
        .map(FieldError::getDefaultMessage)
        .toList();
    return handler(ex, HttpStatus.BAD_REQUEST, String.join("; ", errorMessages));
  }

  @ExceptionHandler(NullPointerException.class)
  public ResponseEntity<ExceptionResponse> handleNullPointerException(NullPointerException ex) {
    return handler(ex, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ExceptionResponse> handleUnreadableMessage(HttpMessageNotReadableException ex) {
    return handler(ex, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMessageNotWritableException.class)
  public ResponseEntity<ExceptionResponse> handleUnreadableMessage(HttpMessageNotWritableException ex) {
    return handler(ex, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ExceptionResponse> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException ex) {
    return handler(ex, HttpStatus.BAD_REQUEST);
  }

}
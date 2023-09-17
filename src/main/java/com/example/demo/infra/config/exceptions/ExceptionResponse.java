package com.example.demo.infra.config.exceptions;

import java.util.List;

import lombok.Builder;

@Builder
public record ExceptionResponse(
    Integer code,
    String status,
    String message,
    String timestamp,
    List<String> errors) {
}
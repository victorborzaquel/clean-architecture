package com.example.demo.core.domain._shared.exceptions;

import com.example.demo.core.domain._shared.DomainException;

public class BadRequestException extends DomainException {
  
  BadRequestException(String message) {
    super(message);
  }
  
}

package com.example.demo.core.domain._shared.exceptions;

import com.example.demo.core.domain._shared.DomainException;

public class InvalidTypeException extends DomainException {
  
  public InvalidTypeException(String message) {
    super(message);
  }
  
}

package com.example.demo.core.domain._shared.exceptions;

import com.example.demo.core.domain._shared.DomainException;

public class AlreadyExistsException extends DomainException {
  
  AlreadyExistsException(String message) {
    super(message);
  }
  
}

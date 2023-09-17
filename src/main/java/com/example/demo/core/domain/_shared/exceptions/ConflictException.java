package com.example.demo.core.domain._shared.exceptions;

import com.example.demo.core.domain._shared.DomainException;

public class ConflictException extends DomainException {

  public ConflictException(String message) {
    super(message);
  }

}

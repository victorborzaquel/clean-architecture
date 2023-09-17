package com.example.demo.core.domain._shared.exceptions;

import com.example.demo.core.domain._shared.DomainException;

public class NotFoundException extends DomainException {

  public NotFoundException(String message) {
    super(message);
  }

}

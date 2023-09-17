package com.example.demo.core.domain._shared.exceptions;

import java.util.List;

import com.example.demo.core.domain._shared.DomainException;

public class InvalidPropException extends DomainException {

  private static final String MESSAGE = "Invalid props";

  public InvalidPropException(String message) {
    super(message);
  }

  public InvalidPropException(String message, List<String> errors) {
    super(message, errors);
  }

  public InvalidPropException(List<String> errors) {
    super(MESSAGE, errors);
  }

}

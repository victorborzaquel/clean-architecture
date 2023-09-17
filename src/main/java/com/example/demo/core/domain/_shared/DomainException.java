package com.example.demo.core.domain._shared;

import java.util.List;

public abstract class DomainException extends RuntimeException {
  
  private final List<String> errors;

  protected DomainException(String message) {
    super(message);
    this.errors = List.of();
  }

  protected DomainException(String message, Throwable cause) {
    super(message, cause);
    this.errors = List.of();
  }

  protected DomainException(String message, List<String> errors) {
    super(message);
    this.errors = errors;
  }

  protected DomainException(String message, Throwable cause, List<String> errors) {
    super(message, cause);
    this.errors = errors;
  }

  public List<String> getErrors() {
    return this.errors;
  }

}

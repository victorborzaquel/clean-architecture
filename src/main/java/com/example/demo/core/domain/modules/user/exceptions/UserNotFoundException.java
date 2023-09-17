package com.example.demo.core.domain.modules.user.exceptions;

import com.example.demo.core.domain._shared.exceptions.NotFoundException;

public class UserNotFoundException extends NotFoundException {

  private static final String MESSAGE = "User not found";

  public UserNotFoundException() {
    super(MESSAGE);
  }
  
}

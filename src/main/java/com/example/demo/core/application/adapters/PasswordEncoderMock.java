package com.example.demo.core.application.adapters;

import com.example.demo.core.domain.adapters.PasswordEncoderGateway;

public class PasswordEncoderMock implements PasswordEncoderGateway{

  @Override
  public String encode(String password) {
    return password;
  }

  @Override
  public boolean matches(String password, String encodedPassword) {
    return password.equals(encodedPassword);
  }
  
}

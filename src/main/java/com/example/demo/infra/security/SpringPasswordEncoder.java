package com.example.demo.infra.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.core.domain.adapters.PasswordEncoderGateway;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpringPasswordEncoder implements PasswordEncoderGateway {

  private final PasswordEncoder passwordEncoder;

  @Override
  public String encode(String password) {
    return passwordEncoder.encode(password);
  }

  @Override
  public boolean matches(String password, String encodedPassword) {
    return passwordEncoder.matches(password, encodedPassword);
  }
  
}

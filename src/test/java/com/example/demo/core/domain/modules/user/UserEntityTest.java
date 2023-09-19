package com.example.demo.core.domain.modules.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.core.domain.adapters.PasswordEncoderGateway;

@ExtendWith(MockitoExtension.class)
class UserEntityTest {
  @Mock
  private PasswordEncoderGateway passwordEncoderGateway;

  UserEntity userEntity;

  UUID id = UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");
  String firstName = "John";
  String lastName = "Doe";
  String fullName = "John Doe";
  String email = "mail@mail.com";
  String password = "password@1234";
  String passwordHash = "hashedPassword";

  @BeforeEach
  void setUp() {
    userEntity = UserEntity.builder()
        .id(id)
        .firstName(firstName)
        .lastName(lastName)
        .email(email)
        .build();
  }

  @Test
  void shouldCreateUserEntity() {
    UserEntity userEntity = UserEntity.builder()
        .id(id)
        .firstName(firstName)
        .lastName(lastName)
        .email(email)
        .build();

    assertEquals(id, userEntity.getId());
    assertEquals(firstName, userEntity.getFirstName());
    assertEquals(lastName, userEntity.getLastName());
    assertEquals(email, userEntity.getEmail());
  }

  @Test
  void shouldHashPassword() {
    when(passwordEncoderGateway.encode(anyString())).thenReturn(passwordHash);

    userEntity.hashPassword(password, passwordEncoderGateway::encode);

    assertEquals(passwordHash, userEntity.getPasswordHash());
  }

  @Test
  void shouldMatchPassword() {
    when(passwordEncoderGateway.matches(anyString(), anyString())).thenReturn(true);

    boolean matches = userEntity.matchesPassword(password, passwordEncoderGateway::matches);

    assertEquals(true, matches);
  }

  @Test
  void shouldSetFirstName() {
    userEntity.setFirstName(firstName);
 
    assertEquals(firstName, userEntity.getFirstName());
  }

  @Test
  void shouldSetLastName() {
    userEntity.setLastName(lastName);

    assertEquals(lastName, userEntity.getLastName());
  }

  @Test
  void shouldSetEmail() {
    userEntity.setEmail(email);

    assertEquals(email, userEntity.getEmail());
  }
}

package com.example.demo.core.domain.modules.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.core.domain.modules.user.payload.request.CreateUserRequestPayload;
import com.example.demo.core.domain.modules.user.payload.request.UpdateUserRequestPayload;
import com.example.demo.core.domain.modules.user.payload.response.UserResponsePayload;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {
  
  @Test
  void shouldMapUserEntityToUserResponsePayload() {
    // given
    UserEntity userEntity = UserEntity.builder()
        .id(UUID.randomUUID())
        .firstName("John")
        .lastName("Doe")
        .email("victor@mail.com")
        .build();
    // when
    UserResponsePayload response = UserMapper.toResponse(userEntity);
    // then
    assertEquals(userEntity.getId(), response.getId());
    assertEquals(userEntity.getFullName(), response.getFullName());
    assertEquals(userEntity.getEmail(), response.getEmail());
  }

  @Test
  void shouldMapUserEntityListToUserResponsePayloadList() {
    // given
    UserEntity userEntity1 = UserEntity.builder()
        .id(UUID.randomUUID())
        .firstName("John")
        .lastName("Doe")
        .email("victor@mail.com")
        .build();

    UserEntity userEntity2 = UserEntity.builder()
        .id(UUID.randomUUID())
        .firstName("Doe")
        .lastName("John")
        .email("mail@mail.com")
        .build();
    // when
    List<UserResponsePayload> response = UserMapper.toResponse(List.of(userEntity1, userEntity2));
    // then
    assertEquals(userEntity1.getId(), response.get(0).getId());
    assertEquals(userEntity1.getFullName(), response.get(0).getFullName());
    assertEquals(userEntity1.getEmail(), response.get(0).getEmail());

    assertEquals(userEntity2.getId(), response.get(1).getId());
    assertEquals(userEntity2.getFullName(), response.get(1).getFullName());
    assertEquals(userEntity2.getEmail(), response.get(1).getEmail());
  }

  @Test
  void shouldMapCreateUserRequestPayloadToUserEntity() {
    // given
    CreateUserRequestPayload payload = CreateUserRequestPayload.builder()
        .firstName("John")
        .lastName("Doe")
        .email("mail@mail.com")
        .build();
    // when
    UserEntity entity = UserMapper.toEntity(payload);
    // then
    assertEquals(payload.firstName(), entity.getFirstName());
    assertEquals(payload.lastName(), entity.getLastName());
    assertEquals(payload.email(), entity.getEmail());
  }

  @Test
  void shouldMapUpdateUserRequestPayloadToUserEntity() {
    // given
    UUID id = UUID.randomUUID();
    UpdateUserRequestPayload payload = UpdateUserRequestPayload.builder()
        .firstName("John")
        .lastName("Doe")
        .email("mail@mail.com")
        .build();
    // when
    UserEntity entity = UserMapper.toEntity(id, payload);
    // then
    assertEquals(id, entity.getId());
    assertEquals(payload.firstName(), entity.getFirstName());
    assertEquals(payload.lastName(), entity.getLastName());
    assertEquals(payload.email(), entity.getEmail());
  }
}

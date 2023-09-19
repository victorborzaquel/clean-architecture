package com.example.demo.core.domain.modules.user;

import java.util.List;
import java.util.UUID;

import com.example.demo.core.domain.modules.user.payload.request.CreateUserRequestPayload;
import com.example.demo.core.domain.modules.user.payload.request.UpdateUserRequestPayload;
import com.example.demo.core.domain.modules.user.payload.response.UserResponsePayload;

public class UserMapper {

  private UserMapper() {
  }

  public static List<UserResponsePayload> toResponse(List<UserEntity> entity) {
    return entity.stream().map(UserMapper::toResponse).toList();
  }

  public static UserResponsePayload toResponse(UserEntity entity) {
    return UserResponsePayload.builder()
        .id(entity.getId())
        .fullName(entity.getFullName())
        .email(entity.getEmail())
        .build();
  }

  public static UserEntity toEntity(CreateUserRequestPayload payload) {
    return UserEntity.builder()
        .firstName(payload.firstName())
        .lastName(payload.lastName())
        .email(payload.email())
        .build();
  }

  public static UserEntity toEntity(UUID id, UpdateUserRequestPayload payload) {
    return UserEntity.builder()
        .id(id)
        .firstName(payload.firstName())
        .lastName(payload.lastName())
        .email(payload.email())
        .build();
  }

}

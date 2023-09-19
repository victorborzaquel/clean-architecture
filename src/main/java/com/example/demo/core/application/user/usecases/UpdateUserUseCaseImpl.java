package com.example.demo.core.application.user.usecases;

import java.util.UUID;

import org.springframework.cache.annotation.CacheEvict;

import com.example.demo.core.domain.modules.user.UserEntity;
import com.example.demo.core.domain.modules.user.UserMapper;
import com.example.demo.core.domain.modules.user.UserRepositoryGateway;
import com.example.demo.core.domain.modules.user.payload.request.UpdateUserRequestPayload;
import com.example.demo.core.domain.modules.user.payload.response.UserResponsePayload;
import com.example.demo.core.domain.modules.user.usecases.UpdateUserUseCase;

public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

  private final UserRepositoryGateway repository;

  public UpdateUserUseCaseImpl(UserRepositoryGateway repository) {
    this.repository = repository;
  }

  @Override
  @CacheEvict(value = "user", key = "#id")
  public UserResponsePayload execute(UUID id, UpdateUserRequestPayload payload) {
    UserEntity entity = UserMapper.toEntity(id, payload);

    UserEntity saved = repository.save(entity);

    return UserMapper.toResponse(saved);
  }

}

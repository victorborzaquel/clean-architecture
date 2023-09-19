package com.example.demo.core.application.user.usecases;

import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;

import com.example.demo.core.domain.modules.user.UserEntity;
import com.example.demo.core.domain.modules.user.UserMapper;
import com.example.demo.core.domain.modules.user.UserRepositoryGateway;
import com.example.demo.core.domain.modules.user.payload.response.UserResponsePayload;
import com.example.demo.core.domain.modules.user.usecases.FindUserUseCase;

public class FindUserUseCaseImpl implements FindUserUseCase {

  private final UserRepositoryGateway repository;

  public FindUserUseCaseImpl(UserRepositoryGateway repository) {
    this.repository = repository;
  }

  @Override
  @Cacheable(value = "user", key = "#id")
  public UserResponsePayload execute(UUID id) {
    UserEntity entity = repository.find(id);

    return UserMapper.toResponse(entity);
  }

}

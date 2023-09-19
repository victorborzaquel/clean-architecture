package com.example.demo.core.application.user.usecases;

import com.example.demo.core.domain.adapters.PasswordEncoderGateway;
import com.example.demo.core.domain.modules.user.UserEntity;
import com.example.demo.core.domain.modules.user.UserMapper;
import com.example.demo.core.domain.modules.user.UserRepositoryGateway;
import com.example.demo.core.domain.modules.user.payload.request.CreateUserRequestPayload;
import com.example.demo.core.domain.modules.user.payload.response.UserResponsePayload;
import com.example.demo.core.domain.modules.user.usecases.CreateUserUseCase;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

  private final UserRepositoryGateway repository;
  private final PasswordEncoderGateway passwordEncoder;

  public CreateUserUseCaseImpl(UserRepositoryGateway repository, PasswordEncoderGateway passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserResponsePayload execute(CreateUserRequestPayload payload) {
    UserEntity entity = UserMapper.toEntity(payload);
    
    entity.hashPassword(payload.password(), passwordEncoder::encode);

    repository.save(entity);

    return UserMapper.toResponse(entity);
  }

}

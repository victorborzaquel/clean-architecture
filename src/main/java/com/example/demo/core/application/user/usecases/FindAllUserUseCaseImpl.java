package com.example.demo.core.application.user.usecases;

import java.util.List;

import com.example.demo.core.domain.modules.user.UserEntity;
import com.example.demo.core.domain.modules.user.UserMapper;
import com.example.demo.core.domain.modules.user.UserRepository;
import com.example.demo.core.domain.modules.user.payload.response.UserResponsePayload;
import com.example.demo.core.domain.modules.user.usecases.FindAllUserUseCase;

public class FindAllUserUseCaseImpl implements FindAllUserUseCase {

  private final UserRepository repository;

  public FindAllUserUseCaseImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<UserResponsePayload> execute() {
    List<UserEntity> entity = repository.findAll();

    return UserMapper.toResponse(entity);
  }

}

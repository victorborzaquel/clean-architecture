package com.example.demo.core.application.user;

import java.util.List;
import java.util.UUID;

import com.example.demo.core.domain.modules.user.UserEntity;
import com.example.demo.core.domain.modules.user.UserRepositoryGateway;

public class UserRepositoryMock implements UserRepositoryGateway {

  private List<UserEntity> entities;

  @Override
  public UserEntity save(UserEntity entity) {
    entities.add(entity);
    return entity;
  }

  @Override
  public UserEntity find(UUID id) {
    return entities.stream().filter(entity -> entity.getId().equals(id)).findFirst().orElse(null);
  }

  @Override
  public List<UserEntity> findAll() {
    return entities;
  }
  
}

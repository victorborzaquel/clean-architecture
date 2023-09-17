package com.example.demo.infra.database.jpa.user;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.core.domain.modules.user.UserEntity;
import com.example.demo.core.domain.modules.user.UserRepository;
import com.example.demo.core.domain.modules.user.exceptions.UserNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Service
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements UserRepository {

  private final JpaUserRepository jpaRepository;

  @Override
  public UserEntity save(UserEntity entity) {
    JpaUser db = Mapper.toDatabase(entity);

    JpaUser saved = jpaRepository.save(db);

    return Mapper.toEntity(saved);
  }

  @Override
  public UserEntity find(UUID id) {
    JpaUser db = jpaRepository.findById(id).orElseThrow(UserNotFoundException::new);

    return Mapper.toEntity(db);
  }

  @Override
  public List<UserEntity> findAll() {
    List<JpaUser> db = jpaRepository.findAll();

    return Mapper.toEntity(db);
  }

  @Value
  public static class Mapper {

    private Mapper() {
    }

    public static List<UserEntity> toEntity(List<JpaUser> db) {
      return db.stream().map(Mapper::toEntity).toList();
    }

    public static UserEntity toEntity(JpaUser db) {
      return UserEntity.builder()
          .id(db.getId())
          .firstName(db.getFirstName())
          .lastName(db.getLastName())
          .email(db.getEmail())
          .password(db.getPasswordHash())
          .build();
    }

    public static JpaUser toDatabase(UserEntity entity) {
      return JpaUser.builder()
          .id(entity.getId())
          .firstName(entity.getFirstName())
          .lastName(entity.getLastName())
          .email(entity.getEmail())
          .passwordHash(entity.getPasswordHash())
          .build();
    }

  }

}

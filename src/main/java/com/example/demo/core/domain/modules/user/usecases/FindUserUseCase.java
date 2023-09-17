package com.example.demo.core.domain.modules.user.usecases;

import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;

import com.example.demo.core.domain.modules.user.payload.response.UserResponsePayload;

public interface FindUserUseCase {

  UserResponsePayload execute(UUID id);

}

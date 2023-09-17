package com.example.demo.core.domain.modules.user.usecases;

import java.util.UUID;

import org.springframework.cache.annotation.CacheEvict;

import com.example.demo.core.domain.modules.user.payload.request.UpdateUserRequestPayload;
import com.example.demo.core.domain.modules.user.payload.response.UserResponsePayload;

public interface UpdateUserUseCase {

  UserResponsePayload execute(UUID id, UpdateUserRequestPayload payload);

}

package com.example.demo.core.domain.modules.user.usecases;

import org.springframework.cache.annotation.Cacheable;

import com.example.demo.core.domain.modules.user.payload.request.CreateUserRequestPayload;
import com.example.demo.core.domain.modules.user.payload.response.UserResponsePayload;

public interface CreateUserUseCase {

  UserResponsePayload execute(CreateUserRequestPayload payload);

}

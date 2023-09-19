package com.example.demo.core.domain.modules.user.usecases;

import com.example.demo.core.domain.modules.user.payload.request.CreateUserRequestPayload;
import com.example.demo.core.domain.modules.user.payload.response.UserResponsePayload;

public interface CreateUserUseCase {

  UserResponsePayload execute(CreateUserRequestPayload payload);

}

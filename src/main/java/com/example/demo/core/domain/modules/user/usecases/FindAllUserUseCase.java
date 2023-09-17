package com.example.demo.core.domain.modules.user.usecases;

import java.util.List;

import com.example.demo.core.domain.modules.user.payload.response.UserResponsePayload;

public interface FindAllUserUseCase {

  List<UserResponsePayload> execute();

}

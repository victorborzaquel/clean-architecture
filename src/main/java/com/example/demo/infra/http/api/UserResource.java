package com.example.demo.infra.http.api;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.domain.modules.user.payload.request.CreateUserRequestPayload;
import com.example.demo.core.domain.modules.user.payload.request.UpdateUserRequestPayload;
import com.example.demo.core.domain.modules.user.payload.response.UserResponsePayload;
import com.example.demo.core.domain.modules.user.usecases.CreateUserUseCase;
import com.example.demo.core.domain.modules.user.usecases.FindAllUserUseCase;
import com.example.demo.core.domain.modules.user.usecases.FindUserUseCase;
import com.example.demo.core.domain.modules.user.usecases.UpdateUserUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserResource {

  private final CreateUserUseCase createUserUseCase;
  private final FindAllUserUseCase findAllUserUseCase;
  private final FindUserUseCase findUserUseCase;
  private final UpdateUserUseCase updateUserUseCase;

  @PostMapping
  public UserResponsePayload create(@Valid @RequestBody CreateUserRequestPayload payload) {
    return this.createUserUseCase.execute(payload);
  }

  @GetMapping
  public List<UserResponsePayload> findAll() {
    return this.findAllUserUseCase.execute();
  }

  @GetMapping("{id}")
  public UserResponsePayload find(@PathVariable("id") UUID id) {
    return this.findUserUseCase.execute(id);
  }

  @PutMapping("{id}")
  public UserResponsePayload update(
      @PathVariable("id") UUID id,
      @Valid @RequestBody UpdateUserRequestPayload payload) {
    return this.updateUserUseCase.execute(id, payload);
  }

}

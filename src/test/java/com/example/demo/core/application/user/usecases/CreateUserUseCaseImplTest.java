package com.example.demo.core.application.user.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.core.application.adapters.PasswordEncoderMock;
import com.example.demo.core.application.user.UserRepositoryMock;
import com.example.demo.core.domain.adapters.PasswordEncoderGateway;
import com.example.demo.core.domain.modules.user.UserEntity;
import com.example.demo.core.domain.modules.user.UserRepositoryGateway;
import com.example.demo.core.domain.modules.user.payload.request.CreateUserRequestPayload;
import com.example.demo.core.domain.modules.user.payload.response.UserResponsePayload;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {
  @Mock
  private UserRepositoryMock repository;
  @Mock
  private PasswordEncoderMock encoder;
  @InjectMocks
  private CreateUserUseCaseImpl useCase;

  @Test
  void shouldCreateUser() {
    UserEntity mockUserEntity = mock(UserEntity.class);
    doNothing().when(mockUserEntity).hashPassword(anyString(), encoder::encode);

    CreateUserRequestPayload payload = CreateUserRequestPayload.builder()
        .firstName("John")
        .lastName("Doe")
        .email("mail@mail.com")
        .password("123456")
        .build();

    UserResponsePayload response = useCase.execute(payload);

    verify(mockUserEntity, times(1)).hashPassword("123456", encoder::encode);
    verify(repository, times(1)).save(any(UserEntity.class));

    assertEquals("John Doe", response.getFullName());
    assertEquals("mail@mail.com", response.getEmail());
    assertEquals(notNull(), response.getId());
  }
}

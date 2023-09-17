package com.example.demo.infra.config.usecases;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.core.application.user.usecases.CreateUserUseCaseImpl;
import com.example.demo.core.application.user.usecases.FindAllUserUseCaseImpl;
import com.example.demo.core.application.user.usecases.FindUserUseCaseImpl;
import com.example.demo.core.application.user.usecases.UpdateUserUseCaseImpl;
import com.example.demo.core.domain.adapters.PasswordEncoderGateway;
import com.example.demo.core.domain.modules.user.UserRepository;
import com.example.demo.core.domain.modules.user.usecases.CreateUserUseCase;
import com.example.demo.core.domain.modules.user.usecases.FindAllUserUseCase;
import com.example.demo.core.domain.modules.user.usecases.FindUserUseCase;
import com.example.demo.core.domain.modules.user.usecases.UpdateUserUseCase;

@Configuration
public class UserUseCaseConfig {

  @Bean
  CreateUserUseCase createUserUseCase(UserRepository repository, PasswordEncoderGateway encoder) {
    return new CreateUserUseCaseImpl(repository, encoder);
  }

  @Bean
  FindAllUserUseCase findAllUserUseCase(UserRepository repository) {
    return new FindAllUserUseCaseImpl(repository);
  }

  @Bean
  FindUserUseCase findUserUseCase(UserRepository repository) {
    return new FindUserUseCaseImpl(repository);
  }

  @Bean
  UpdateUserUseCase updateUserUseCase(UserRepository repository) {
    return new UpdateUserUseCaseImpl(repository);
  }

}

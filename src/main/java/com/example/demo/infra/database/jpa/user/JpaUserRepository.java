package com.example.demo.infra.database.jpa.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<JpaUser, UUID> {

}

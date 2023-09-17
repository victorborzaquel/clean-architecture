package com.example.demo.core.domain.modules.user.payload.response;

import java.io.Serializable;
import java.util.UUID;

public class UserResponsePayload implements Serializable {

    private final UUID id;
    private final String fullName;
    private final String email;

    private UserResponsePayload(UUID id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public UUID getId() {
        return this.id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        UUID id;
        String fullName;
        String email;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public UserResponsePayload build() {
            return new UserResponsePayload(id, fullName, email);
        }
        
    }

}

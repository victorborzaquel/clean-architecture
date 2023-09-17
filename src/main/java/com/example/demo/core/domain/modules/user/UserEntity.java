package com.example.demo.core.domain.modules.user;

import java.util.Objects;
import java.util.UUID;

import com.example.demo.core.domain._shared.Validate;
import com.example.demo.core.domain.adapters.PasswordEncoderGateway;

public class UserEntity {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;

    private UserEntity(UUID id, String firstName, String lastName, String email, String passwordHash) {
        this.id = id != null ? id : UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        validate();
    }

    private void validate() {
        Validate.of(id, "id").uuid()
                .and(firstName, "firstName").name()
                .and(lastName, "lastName").name()
                .and(email, "email").email()
                .throwException();
    }


    public void hashPassword(String password, PasswordEncoderGateway.Encode encode) {
        Validate.of(password, "password").password().throwException();
        this.passwordHash = encode.execute(password);
    }

    public boolean matchesPassword(String password, PasswordEncoderGateway.Matches matches) {
        return matches.execute(password, this.passwordHash);
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        validate();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        validate();
    }

    public void setEmail(String email) {
        this.email = email;
        validate();
    }

    public void setPasswordHash(String password) {
        this.passwordHash = password;
        validate();
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserEntity)) {
            return false;
        }
        return id.equals(((UserEntity) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private UUID id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(id, firstName, lastName, email, password);
        }
    }
}

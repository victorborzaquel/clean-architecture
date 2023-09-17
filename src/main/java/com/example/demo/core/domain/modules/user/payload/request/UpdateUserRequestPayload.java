package com.example.demo.core.domain.modules.user.payload.request;

public class UpdateUserRequestPayload {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    private UpdateUserRequestPayload(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String firstName() {
        return this.firstName;
    }

    public String lastName() {
        return this.lastName;
    }

    public String email() {
        return this.email;
    }

    public String password() {
        return this.password;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        String firstName;
        String lastName;
        String email;
        String password;

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

        public UpdateUserRequestPayload build() {
            return new UpdateUserRequestPayload(firstName, lastName, email, password);
        }
    }

}

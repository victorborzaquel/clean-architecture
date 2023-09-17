package com.example.demo.core.domain.adapters;

public interface PasswordEncoderGateway {

    String encode(String password);

    boolean matches(String password, String encodedPassword);

    @FunctionalInterface
    public interface Encode {
        String execute(String password);
    }

    @FunctionalInterface
    public interface Matches {
        boolean execute(String password, String encodedPassword);
    }

}

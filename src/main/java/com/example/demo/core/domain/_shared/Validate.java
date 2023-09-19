package com.example.demo.core.domain._shared;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.demo.core.domain._shared.exceptions.InvalidPropException;
import com.example.demo.core.domain._shared.exceptions.InvalidTypeException;

public class Validate<T> {

  private T prop;
  private String propName;
  private List<String> errors = new ArrayList<>();

  public static <T> Validate<T> of(T prop, String propName) {
    return new Validate<>(prop, propName);
  }

  private Validate(T t, String propName, List<String> errors) {
    this.prop = t;
    this.propName = propName;
    this.errors = errors;
  }

  private Validate(T t, String propName) {
    this.prop = t;
    this.propName = propName;
  }

  public <N> Validate<N> and(N prop, String propName) {
    return new Validate<>(prop, propName, this.errors);
  }

  public T get() {
    throwException();

    return this.prop;
  }

  public String getTrim() {
    throwException();

    return getString().trim();
  }

  public void throwException() {
    if (!errors.isEmpty()) {
      throw new InvalidPropException(errors);
    }
  }

  private String getString() {
    if (!(this.prop instanceof String)) {
      throw new InvalidTypeException(propName + " must be a string");
    }

    return (String) this.prop;
  }

  private Integer getInteger() {
    if (!(this.prop instanceof Integer)) {
      throw new InvalidTypeException(propName + " must be an integer");
    }

    return (Integer) this.prop;
  }

  private UUID getUUID() {
    if (!(this.prop instanceof UUID)) {
      throw new InvalidTypeException(propName + " must be a UUID");
    }

    return (UUID) this.prop;
  }

  public Validate<T> username() {
    String string = getString();

    minSize(3);
    maxSize(20);

    if (!string.matches("^[a-zA-Z0-9]*$")) {
      errors.add("Username must contain only letters and numbers");
    }

    if (string.matches("^[\\d]*$")) {
      errors.add("Username must contain at least one letter");
    }

    return this;
  }

  public Validate<T> email() {
    String string = getString();

    if (!string.matches("^(.+)@(.+)$")) {
      errors.add("Invalid email");
    }

    return this;
  }

  public Validate<T> notBlank() {
    String string = getString();

    if (string.isBlank()) {
      errors.add(String.format("%s is required", propName));
    }

    return this;
  }

  public Validate<T> notNull() {
    if (this.prop == null) {
      errors.add(String.format("%s is required", propName));
    }

    return this;
  }

  public Validate<T> notEmpty() {
    String string = getString();

    if (string.isEmpty()) {
      errors.add(propName + " is required");
    }

    return this;
  }

  public Validate<T> minSize(int min) {
    String string = getString();

    if (string.length() < min) {
      errors.add(String.format("%s must be at least %d characters long", propName, min));
    }

    return this;
  }

  public Validate<T> maxSize(int max) {
    String string = getString();

    if (string.length() > max) {
      errors.add(String.format("%s must be at most %d characters long", propName, max));
    }

    return this;
  }

  public Validate<T> min(int min) {
    Integer integer = getInteger();

    if (integer < min) {
      errors.add(String.format("%s must be at least %d", propName, min));
    }

    return this;
  }

  public Validate<T> max(int max) {
    Integer integer = getInteger();

    if (integer > max) {
      errors.add(String.format("%s must be at most %d", propName, max));
    }

    return this;
  }

  public Validate<T> range(int min, int max) {
    if (min > max) {
      errors.add("Min must be less than or equal to max");
    }

    Integer integer = getInteger();

    if (integer < min || integer > max) {
      errors.add(String.format("%s must be between %d and %d", propName, min, max));
    }

    return this;
  }

  public Validate<T> password() {
    String string = getString();

    minSize(8);
    maxSize(20);

    boolean hasUppercase = !string.equals(string.toLowerCase());
    boolean hasLowercase = !string.equals(string.toUpperCase());
    boolean hasNumber = string.matches(".*\\d.*");
    boolean hasSpecial = !string.matches("[A-Za-z0-9 ]*");

    if (!hasUppercase) {
      errors.add("Password must contain at least one uppercase letter");
    }

    if (!hasLowercase) {
      errors.add("Password must contain at least one lowercase letter");
    }

    if (!hasNumber) {
      errors.add("Password must contain at least one number");
    }

    if (!hasSpecial) {
      errors.add("Password must contain at least one special character");
    }

    return this;
  }

  public Validate<T> name() {
    String string = getString();

    minSize(3);
    maxSize(20);

    if (!string.matches("^[a-zA-Z ]*$")) {
      errors.add(propName + " must contain only letters");
    }

    return this;
  }

  public Validate<T> uuid() {
    UUID uuid = getUUID();
    String string = uuid.toString();

    if (!string.matches("^[a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8}$")) {
      errors.add(propName + " must be a valid UUID");
    }

    return this;
  }

  public Validate<T> uuidString() {
    String string = getString();

    validateUUID(string);

    return this;
  }
}

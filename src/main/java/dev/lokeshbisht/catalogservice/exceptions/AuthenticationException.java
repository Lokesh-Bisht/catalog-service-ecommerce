package dev.lokeshbisht.catalogservice.exceptions;

public class AuthenticationException extends RuntimeException {

  private static final Long serialVersionID = 1L;

  public AuthenticationException(String message) {
    super(message);
  }
}

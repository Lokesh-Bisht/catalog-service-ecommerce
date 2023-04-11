package dev.lokeshbisht.catalogservice.exceptions;

public class AuthorizationException extends RuntimeException {

  private static final Long serialVersionID = 1L;

  public AuthorizationException(String message) {
    super(message);
  }
}

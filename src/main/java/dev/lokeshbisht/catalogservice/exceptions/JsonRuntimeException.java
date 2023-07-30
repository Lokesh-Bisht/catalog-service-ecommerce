package dev.lokeshbisht.catalogservice.exceptions;

import java.io.Serial;

public class JsonRuntimeException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  public JsonRuntimeException(String message, Throwable throwable) {
    super(message, throwable);
  }
}

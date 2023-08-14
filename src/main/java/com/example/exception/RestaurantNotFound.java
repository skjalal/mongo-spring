package com.example.exception;

import java.io.Serial;

public class RestaurantNotFound extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -7867190745766939L;

  public RestaurantNotFound() {
    super("Restaurant not found");
  }
}

package com.thanhbang.backend.exceptions;

public class RefreshTokenNotFoundException extends RuntimeException {
  public RefreshTokenNotFoundException(String refreshToken) {
    super("Refresh token is invalid!: " + refreshToken);
  }
}

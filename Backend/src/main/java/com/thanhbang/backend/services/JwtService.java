package com.thanhbang.backend.services;

import org.springframework.security.core.userdetails.UserDetails;

import com.thanhbang.backend.entities.User;

public interface JwtService {
  String extractUserName(String token);

  String generateToken(UserDetails userDetails);

  String generateRefreshToken(UserDetails userDetails);

  User getUser(String token);

  boolean isTokenValid(String token, UserDetails userDetails);
}

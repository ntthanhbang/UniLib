package com.thanhbang.backend.services.imp;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thanhbang.backend.daos.request.RefreshRequest;
import com.thanhbang.backend.daos.request.SigningRequest;
import com.thanhbang.backend.daos.response.JwtAuthenticationResponse;
import com.thanhbang.backend.entities.Token;
import com.thanhbang.backend.repositories.TokenRepository;
import com.thanhbang.backend.exceptions.RefreshTokenNotFoundException;
import com.thanhbang.backend.repositories.UsersRepositories;
import com.thanhbang.backend.services.AuthenticationService;
import com.thanhbang.backend.services.JwtService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
  private final UsersRepositories userRepository;
  private final TokenRepository tokenRepository;
  private final JwtService jwtService;

  private final AuthenticationManager authenticationManager;

  @Value("${token.access.token.expiration}")
  private long accessTokenExpiration;

  @Transactional
  @Override
  public JwtAuthenticationResponse signing(SigningRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
    List<Token> activeTokens = tokenRepository.findAllByUserAndIsActive(user, true).orElse(Collections.emptyList());
    if (!activeTokens.isEmpty()) {
      for (Token activeToken : activeTokens) {
        activeToken.setIsActive(false);
      }
      tokenRepository.saveAll(activeTokens);
    }
    var jwt = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    var expiresAt = new Date(System.currentTimeMillis() + accessTokenExpiration);
    tokenRepository.save(new Token(jwt, refreshToken, expiresAt, user, true));
    return JwtAuthenticationResponse.builder().token(jwt).refreshToken(refreshToken).expiresAt(expiresAt).build();
  }

  @Override
  public JwtAuthenticationResponse refreshToken(RefreshRequest request) {
    Token token = tokenRepository.findByRefreshToken(request.getRefreshToken())
        .orElseThrow(() -> new RefreshTokenNotFoundException(request.getRefreshToken()));
    if (token.getExpiresAt().after(new Date()) && token.getIsActive()) {
      var user = token.getUser();
      var newAccessToken = jwtService.generateToken(user);
      var newRefreshToken = jwtService.generateRefreshToken(user);
      var expiresAt = new Date(System.currentTimeMillis() + accessTokenExpiration);
      token.setIsActive(false);
      tokenRepository.save(token);
      tokenRepository.save(new Token(newAccessToken, newRefreshToken, expiresAt, user, true));
      return new JwtAuthenticationResponse(newAccessToken, newRefreshToken, expiresAt);

    } else {
      tokenRepository.delete(token);
      return null;
    }
  }
}

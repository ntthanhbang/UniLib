package com.thanhbang.backend.controllers;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce.Cluster.Refresh;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhbang.backend.daos.request.RefreshRequest;
import com.thanhbang.backend.daos.request.SigningRequest;
import com.thanhbang.backend.daos.response.JwtAuthenticationResponse;
import com.thanhbang.backend.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthenticationService authenticationService;

  @PostMapping("/login")
  public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody SigningRequest reg) {
    return ResponseEntity.ok(authenticationService.signing(reg));
  }

  @PostMapping("/refresh")
  public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshRequest reg) {
    return ResponseEntity.ok(authenticationService.refreshToken(reg));
  }
}

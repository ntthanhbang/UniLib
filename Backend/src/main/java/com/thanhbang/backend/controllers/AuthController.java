package com.thanhbang.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhbang.backend.daos.request.RefreshRequest;
import com.thanhbang.backend.daos.request.SigningRequest;
import com.thanhbang.backend.daos.response.JwtAuthenticationResponse;
import com.thanhbang.backend.daos.response.UserDataResponse;
import com.thanhbang.backend.entities.User;
import com.thanhbang.backend.services.AuthenticationService;
import com.thanhbang.backend.services.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthenticationService authenticationService;
  private final JwtService jwtService;

  @PostMapping("/login")
  public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody SigningRequest reg) {
    return ResponseEntity.ok(authenticationService.signing(reg));
  }

  @PostMapping("/refresh")
  public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshRequest reg) {
    return ResponseEntity.ok(authenticationService.refreshToken(reg));
  }

  @GetMapping("/me")
  public ResponseEntity<UserDataResponse> getUser(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      String jwtString = authHeader.substring(7);
      User userData = jwtService.getUser(jwtString);
      String email = userData.getEmail();
      String firstname = userData.getFirstName();
      String lastname = userData.getFirstName();
      String role = userData.getRole().toString();
      UserDataResponse res = new UserDataResponse();
      res.setFirstName(firstname);
      res.setEmail(email);
      res.setLastName(lastname);
      res.setRole(role);
      return ResponseEntity.ok(res);
    } else {
      return ResponseEntity.status(401).build();
    }
  }
}

package com.thanhbang.backend.services;

import com.thanhbang.backend.daos.request.RefreshRequest;
import com.thanhbang.backend.daos.request.SigningRequest;
import com.thanhbang.backend.daos.response.JwtAuthenticationResponse;

public interface AuthenticationService {
  JwtAuthenticationResponse signing(SigningRequest request);

  JwtAuthenticationResponse refreshToken(RefreshRequest request);
}
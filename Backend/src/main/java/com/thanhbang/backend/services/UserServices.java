package com.thanhbang.backend.services;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServices {
  UserDetailsService userDetailsService();
}

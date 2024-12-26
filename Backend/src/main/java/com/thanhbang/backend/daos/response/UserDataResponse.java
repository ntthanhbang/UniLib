package com.thanhbang.backend.daos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDataResponse {
  private String firstName;
  private String lastName;
  private String email;
  private String role;
}

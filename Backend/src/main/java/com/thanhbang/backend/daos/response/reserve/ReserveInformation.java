package com.thanhbang.backend.daos.response.reserve;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReserveInformation {
  public String BookName;
  public LocalDate start;
  public LocalDate end;
}

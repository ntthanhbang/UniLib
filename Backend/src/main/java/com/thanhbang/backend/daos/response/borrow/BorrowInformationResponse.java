package com.thanhbang.backend.daos.response.borrow;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowInformationResponse {
  private long BorrowId;
  private long bookId;
  private String bookName;
  private String fullName;
  private LocalDate begin;
  private LocalDate end;
}

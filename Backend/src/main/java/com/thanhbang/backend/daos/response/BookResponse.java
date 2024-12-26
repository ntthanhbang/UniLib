package com.thanhbang.backend.daos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
  private long bookId;
  private String bookName;
  private String author;
  private String bookUrl;
}
package com.thanhbang.backend.daos.request;

import java.io.StringWriter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class NewBorrowRequest {
  private Long bookId;
}

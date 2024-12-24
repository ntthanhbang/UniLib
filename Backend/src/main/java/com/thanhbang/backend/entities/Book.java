package com.thanhbang.backend.entities;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long bookId;

  @Column
  @NonNull
  private String bookName;

  @Column
  @NonNull
  private String authors;

  @Column
  @NonNull
  private String bookCoverUrl;
}
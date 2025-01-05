package com.thanhbang.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.util.*;

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

  @Enumerated
  BookStatus bookStatus;

  @Column
  @NonNull
  private String bookName;

  @Column
  @NonNull
  private String authors;

  @Lob
  @Column(length = 10000)
  @NonNull
  private String description;

  @Lob
  @Column(length = 10000)
  @NonNull
  private String bookCoverUrl;

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  @JsonIgnore
  private List<Borrow> borrower = new ArrayList<>();

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  @JsonIgnore
  private List<ReserveBook> Reserver = new ArrayList<>();
}
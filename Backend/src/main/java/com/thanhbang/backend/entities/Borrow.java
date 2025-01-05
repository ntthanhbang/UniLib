package com.thanhbang.backend.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "borrow")
public class Borrow {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long borrowId;

  @Enumerated
  private BorrowStatus borrowStatus;

  @Column
  private LocalDate pickUpDate;

  @Column
  private LocalDate returnDate;

  @ManyToOne
  @JoinColumn(name = "id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "book_id")
  private Book book;
}

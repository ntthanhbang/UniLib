package com.thanhbang.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ReserveBook {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long ReserveId;

  @Column
  private LocalDate ReserveDate;

  @Column
  private LocalDate ReserveUntil;

  @ManyToOne
  @JoinColumn(name = "id")
  @JsonIgnore
  private User user;

  @ManyToOne
  @JoinColumn(name = "book_id")
  @JsonIgnore
  private Book book;
}

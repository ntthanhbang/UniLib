package com.thanhbang.backend.services;

import com.thanhbang.backend.entities.Book;
import com.thanhbang.backend.entities.Borrow;
import com.thanhbang.backend.entities.ReserveBook;
import com.thanhbang.backend.entities.User;
import com.thanhbang.backend.repositories.BookRepository;
import com.thanhbang.backend.repositories.ReserveBookRepository;
import java.time.LocalDate;
import java.util.*;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReserveBookService {
  private final ReserveBookRepository reserveBookRepository;
  private final JwtService jwtService;
  private final BookRepository bookRepository;

  public List<ReserveBook> getAllReservation() {
    return reserveBookRepository.findAll();
  }

  public List<ReserveBook> getReserveBorrowsByUser(User user) {
    return reserveBookRepository.findByUser(user);
  }

  public ReserveBook creatReserveBook(Long bookId, String jwt) {
    User user = jwtService.getUser(jwt);
    Book book = bookRepository.findById(bookId).get();
    LocalDate today = LocalDate.now();
    LocalDate end = LocalDate.now().plusDays(14);
    ReserveBook Reservation = ReserveBook.builder().book(book).user(user).ReserveDate(today).ReserveUntil(end).build();
    reserveBookRepository.save(Reservation);
    return Reservation;
  }

  public List<ReserveBook> getByBook(Book book) {
    return reserveBookRepository.findByBook(book);
  }
}

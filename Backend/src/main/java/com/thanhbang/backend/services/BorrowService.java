package com.thanhbang.backend.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import com.thanhbang.backend.entities.Book;
import com.thanhbang.backend.entities.BookStatus;
import com.thanhbang.backend.entities.Borrow;
import com.thanhbang.backend.entities.BorrowStatus;
import com.thanhbang.backend.entities.ReserveBook;
import com.thanhbang.backend.entities.User;
import com.thanhbang.backend.repositories.BookRepository;
import com.thanhbang.backend.repositories.BorrowRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BorrowService {
  private final BorrowRepository borrowRepository;
  // private final JwtService jwtService;
  private final BookService bookService;
  private final BookRepository bookRepository;
  private final ReserveBookService reserveBookService;
  private final SendGridMailService sendGridMailService;

  public BorrowService(BorrowRepository borrowRepository, JwtService jwtService, BookService bookService,
      BookRepository bookRepository, ReserveBookService reserveBookService, SendGridMailService sendGridMailService) {
    this.borrowRepository = borrowRepository;
    // this.jwtService = jwtService;
    this.bookService = bookService;
    this.bookRepository = bookRepository;
    this.reserveBookService = reserveBookService;
    this.sendGridMailService = sendGridMailService;
  }

  public List<Borrow> getAllBorrow() {
    return borrowRepository.findAll();
  }

  public Borrow getBorrowById(Long id) {
    return borrowRepository.findById(id).get();
  }

  public Borrow newBorrow(Long bookId, User user) {
    Optional<Book> bookOptional = bookRepository.findById(bookId);

    if (bookOptional.isPresent()) {
      Book book = bookOptional.get();
      if (book.getBookStatus() != BookStatus.UNAVAILABLE && book.getBookStatus() != BookStatus.PICKING) {
        LocalDate today = LocalDate.now();
        LocalDate returnDate = LocalDate.now().plusDays(14);
        Borrow newBorrow = Borrow.builder().book(book).borrowStatus(BorrowStatus.HOLDING).user(user).pickUpDate(today)
            .returnDate(returnDate).build();
        borrowRepository.save(newBorrow);
        bookService.updateBookStatus(book.getBookId(), BookStatus.UNAVAILABLE);
        return newBorrow;
      } else {
        throw new NoSuchElementException("Book is unavailabe" + bookId);
      }
    } else {
      throw new NoSuchElementException("book not found" + bookId);
    }
  }

  public Borrow Return(Long borrowId) {
    Optional<Borrow> borrowFinder = borrowRepository.findById(borrowId);
    if (borrowFinder.isPresent()) {
      Borrow borrow = borrowFinder.get();
      Book book = borrow.getBook();
      List<ReserveBook> reserver = reserveBookService.getByBook(book);

      log.info("People to send email to");
      for (ReserveBook reservant : reserver) {
          String content = "Hello "+ reservant.getUser().getFirstName() +" \n" +"Your book: " + book.getBookName()+ " is available";

          sendGridMailService.sendMail("Your Reserve book is available", content, Collections.singletonList(reservant.getUser().getEmail()), null, null);

      }

      borrow.setBorrowStatus(BorrowStatus.RETURNED);
      book.setBookStatus(BookStatus.AVAILABLE);
      bookRepository.save(book);
      borrowRepository.save(borrow);

      // send email

      return borrow;
    } else {
      throw new NoSuchElementException("Borrow not found" + borrowId);
    }
  }

  public List<Borrow> getMyBorrow(User user) {
    return borrowRepository.findByUser(user);
  }

}

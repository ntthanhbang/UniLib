package com.thanhbang.backend.services;

import org.springframework.stereotype.Service;

import java.util.*;

import com.thanhbang.backend.entities.Book;
import com.thanhbang.backend.entities.Borrow;
import com.thanhbang.backend.entities.User;
import com.thanhbang.backend.repositories.BookRepository;
import com.thanhbang.backend.repositories.BorrowRepository;

@Service
public class BorrowService {
  private final BorrowRepository borrowRepository;
  // private final JwtService jwtService;
  private final BookService bookService;
  private final BookRepository bookRepository;

  public BorrowService(BorrowRepository borrowRepository, JwtService jwtService, BookService bookService,
      BookRepository bookRepository) {
    this.borrowRepository = borrowRepository;
    // this.jwtService = jwtService;
    this.bookService = bookService;
    this.bookRepository = bookRepository;
  }

  public List<Borrow> getAllBorrow() {
    return borrowRepository.findAll();
  }

  public Borrow newBorrow(Long bookId, User user) {
    Optional<Book> bookOptional = bookRepository.findById(bookId);

    if (bookOptional.isPresent()) {
      Book book = bookOptional.get();
      Borrow newBorrow = Borrow.builder().book(book).user(user).build();
      borrowRepository.save(newBorrow);
      return newBorrow;
    } else {
      throw new NoSuchElementException("book not found" + bookId);
    }
  }

}

package com.thanhbang.backend.services;

import org.springframework.stereotype.Service;

import java.util.*;

import com.thanhbang.backend.entities.Book;
import com.thanhbang.backend.entities.BookStatus;
import com.thanhbang.backend.repositories.BookRepository;

@Service
public class BookService {
  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  public Book getById(long id) {
    Book res = bookRepository.findById(id).get();
    return res;
  }

  public List<Book> getBookByName(String name) {
    List<Book> res = bookRepository.findByBookNameContaining(name);
    return res;
  }

  public Book updateBookStatus(Long bookId, BookStatus bookStatus) {
    Optional<Book> bookFinder = bookRepository.findById(bookId);
    if (bookFinder.isPresent()) {
      Book book = bookFinder.get();
      book.setBookStatus(bookStatus);
      bookRepository.save(book);
      return book;
    } else {
      throw new NoSuchElementException("not found" + bookId);
    }
  }
}

package com.thanhbang.backend.services;

import org.springframework.stereotype.Service;
import java.util.*;

import com.thanhbang.backend.entities.Book;
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
}

package com.thanhbang.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.thanhbang.backend.entities.Book;
//import com.thanhbang.backend.daos.response.BookResponse;
import com.thanhbang.backend.services.BookService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
  private final BookService bookService;

  @GetMapping("/")
  public ResponseEntity<List<Book>> getAllBook() {
    return ResponseEntity.ok(bookService.getAllBooks());
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable long id) {
    if (bookService.getById(id) != null) {
      return ResponseEntity.ok(bookService.getById(id));
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/search")
  public ResponseEntity<List<Book>> getBookByName(@RequestParam String q) {
    return ResponseEntity.ok(this.bookService.getBookByName(q));
  }
}

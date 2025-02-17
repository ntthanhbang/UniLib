package com.thanhbang.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhbang.backend.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  public List<Book> findAll();

  public List<Book> findByBookNameContaining(String name);
}

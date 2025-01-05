package com.thanhbang.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

import com.thanhbang.backend.entities.Book;
import com.thanhbang.backend.entities.ReserveBook;
import com.thanhbang.backend.entities.User;

public interface ReserveBookRepository extends JpaRepository<ReserveBook, Long> {
  public List<ReserveBook> findByBook(Book book);

  public List<ReserveBook> findByUser(User user);
}

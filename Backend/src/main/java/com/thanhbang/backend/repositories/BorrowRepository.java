package com.thanhbang.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.thanhbang.backend.entities.Borrow;
import com.thanhbang.backend.entities.User;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
  public List<Borrow> findByUser(User user);
}

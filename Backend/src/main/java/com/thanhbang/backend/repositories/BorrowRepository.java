package com.thanhbang.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhbang.backend.entities.Borrow;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

}

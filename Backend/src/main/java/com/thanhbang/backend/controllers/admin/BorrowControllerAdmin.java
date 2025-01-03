package com.thanhbang.backend.controllers.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhbang.backend.entities.Borrow;
import com.thanhbang.backend.services.BorrowService;

import lombok.RequiredArgsConstructor;

import java.util.*;

@RestController
@RequestMapping("/api/v1/admin/borrow")
@RequiredArgsConstructor
public class BorrowControllerAdmin {
  private final BorrowService borrowService;

  @GetMapping
  public ResponseEntity<List<Borrow>> getAllBorrow() {
    return ResponseEntity.ok(borrowService.getAllBorrow());
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<Borrow> getBorrowById(@PathVariable long id) {
    return ResponseEntity.ok(borrowService.getBorrowById(id));
  }

  @PostMapping("/return/{id}")
  public ResponseEntity<Borrow> returnBook(@PathVariable long id) {
    return ResponseEntity.ok(borrowService.Return(id));
  }

  @PostMapping("/confirm/{id}")
  public ResponseEntity<Borrow> confirmBook(@PathVariable long id) {

    return ResponseEntity.ok(borrowService.Confirm(id));
  }
}

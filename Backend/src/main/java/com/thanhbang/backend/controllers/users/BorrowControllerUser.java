package com.thanhbang.backend.controllers.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(originPatterns = "http://localhost:5173")
@RequestMapping("/api/v1/user/borrow")
public class BorrowControllerUser {

  @GetMapping
  public ResponseEntity<String> hello() {
    return ResponseEntity.ok("hi");
  }
}

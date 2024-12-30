package com.thanhbang.backend.controllers.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.thanhbang.backend.entities.ReserveBook;
import com.thanhbang.backend.entities.User;
import com.thanhbang.backend.services.JwtService;
import com.thanhbang.backend.services.ReserveBookService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:5713")
@RequestMapping("api/v1/user/reserve")
@RequiredArgsConstructor
public class ReserveControllerUser {
  private final JwtService jwtService;
  private final ReserveBookService reserveBookService;

  @GetMapping("/me")
  private ResponseEntity<List<ReserveBook>> getMyReserve(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");
    if (authHeader != null & authHeader.startsWith("Bearer ")) {
      String jwt = authHeader.substring(7);
      User user = jwtService.getUser(jwt);

      return ResponseEntity.ok(reserveBookService.getReserveBorrowsByUser(user));

    } else {
      return ResponseEntity.status(401).build();
    }
  }

  @PostMapping("/create-reserve")
  private ResponseEntity<ReserveBook> createReservation(HttpServletRequest request, @RequestBody long bookId) {
    String authHeader = request.getHeader("Authorization");
    if (authHeader != null & authHeader.startsWith("Bearer ")) {
      String jwt = authHeader.substring(7);
      ReserveBook newRes = reserveBookService.creatReserveBook(bookId, jwt);

      return ResponseEntity.ok(newRes);

    } else {
      return ResponseEntity.status(401).build();
    }
  }
}

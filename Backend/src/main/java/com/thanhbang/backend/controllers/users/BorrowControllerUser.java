package com.thanhbang.backend.controllers.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.thanhbang.backend.daos.request.NewBorrowRequest;
import com.thanhbang.backend.daos.response.borrow.BorrowInformationResponse;
import com.thanhbang.backend.entities.Borrow;
import com.thanhbang.backend.entities.User;
import com.thanhbang.backend.services.BorrowService;
import com.thanhbang.backend.services.JwtService;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1/user/borrow")
@RequiredArgsConstructor
public class BorrowControllerUser {
  private final JwtService jwtService;
  private final BorrowService borrowService;

  @GetMapping
  public ResponseEntity<String> hello() {
    return ResponseEntity.ok("hi");
  }

  @PostMapping("/create-borrow")
  public ResponseEntity<BorrowInformationResponse> createBorrow(@RequestBody NewBorrowRequest bookRequest,
      HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");
    if (authHeader != null & authHeader.startsWith("Bearer ")) {
      String jwt = authHeader.substring(7);
      User user = jwtService.getUser(jwt);
      Borrow newBorrow = borrowService.newBorrow(bookRequest.getBookId(), user);

      BorrowInformationResponse res = BorrowInformationResponse.builder()
          .bookName(newBorrow.getBook().getBookName()).bookId(newBorrow.getBook().getBookId())
          .BorrowId(newBorrow.getBorrowId()).begin(newBorrow.getPickUpDate()).end(newBorrow.getReturnDate())
          .fullName(newBorrow.getUser().getFirstName() + newBorrow.getUser().getLastName()).build();

      return ResponseEntity.ok(res);

    } else {
      return ResponseEntity.status(401).build();
    }

  }

  @GetMapping("/me")
  public ResponseEntity<List<BorrowInformationResponse>> getMyBorrow(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");
    if (authHeader != null & authHeader.startsWith("Bearer ")) {
      String jwt = authHeader.substring(7);
      User user = jwtService.getUser(jwt);
      List<Borrow> myBorrow = borrowService.getMyBorrow(user);

      List<BorrowInformationResponse> res = new ArrayList<>();

      for (Borrow myBorrow2 : myBorrow) {
        BorrowInformationResponse myBorrowRes = BorrowInformationResponse.builder()
            .bookName(myBorrow2.getBook().getBookName()).bookId(myBorrow2.getBook().getBookId())
            .BorrowId(myBorrow2.getBorrowId()).begin(myBorrow2.getPickUpDate()).end(myBorrow2.getReturnDate())
            .fullName(myBorrow2.getUser().getFirstName() + myBorrow2.getUser().getLastName()).build();
        res.add(myBorrowRes);
      }
      return ResponseEntity.ok(res);
    } else {
      return ResponseEntity.status(401).build();
    }
  }
}

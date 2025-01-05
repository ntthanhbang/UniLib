package com.thanhbang.backend.repositories;

import com.thanhbang.backend.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhbang.backend.entities.User;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
  Optional<Token> findByToken(String token);

  Optional<List<Token>> findAllByUserAndIsActive(User user, Boolean isActive);

  Optional<Token> findByRefreshToken(String refreshToken);
}

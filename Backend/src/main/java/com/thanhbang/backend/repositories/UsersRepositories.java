package com.thanhbang.backend.repositories;

import com.thanhbang.backend.entities.User;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UsersRepositories extends CrudRepository<User, Long> {
  Optional<User> findByEmail(String email);
}

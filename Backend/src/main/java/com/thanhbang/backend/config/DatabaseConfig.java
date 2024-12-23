package com.thanhbang.backend.config;

import com.thanhbang.backend.entities.Roles;
import com.thanhbang.backend.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thanhbang.backend.repositories.UsersRepositories;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {
  private final UsersRepositories userRepository;

  @Bean
  public CommandLineRunner init() {
    return args -> {
      userRepository.save(new User("Admin", "Admin", "admin@unilb.com",
          "$2a$12$p9kvnyN7/xr9vYdg1d6n9u7WLygbngfI5Zq1LHqExHpYqTcWgmYqe", Roles.ADMIN)); // admin
                                                                                         // username:admin@unilib.com

      // password:admin

      userRepository.save(new User("Nguyen", "Binh", "thebinhnguyen2703@gmail.com",
          "$2a$12$Qvvr440y.bbKPNVZtgv7leDHbQ.U/yDuglzz7vIEwEY8o3NHDxNpi", Roles.USER)); // password: testuser1
      userRepository.save(new User("Nguyen", "Binh 2", "thebinhnguyen2005@gmail.com",
          "$2a$12$PdlZPhGO0VjplWTkzA0EoOOKhspqSokQcQbVm2OLmbI4lFUeKs28C", Roles.USER)); // password: testuser2
    };
  }
}
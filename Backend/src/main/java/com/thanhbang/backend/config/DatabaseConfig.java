package com.thanhbang.backend.config;

import com.thanhbang.backend.entities.Roles;
import com.thanhbang.backend.entities.User;
import com.opencsv.CSVReader;
import com.thanhbang.backend.entities.Book;
import com.thanhbang.backend.entities.BookStatus;

import java.io.InputStreamReader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.thanhbang.backend.repositories.BookRepository;
import com.thanhbang.backend.repositories.UsersRepositories;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {
  private final UsersRepositories userRepository;
  private final BookRepository bookRepository;

  private final PasswordEncoder passwordEncoder;

  @Bean
  public CommandLineRunner init() {
    return args -> {
      // fake users

      userRepository.save(new User("Admin", "Admin", "admin@unilb.com",
          passwordEncoder.encode("admin"), Roles.ADMIN)); // admin
      // username:admin@unilib.com

      // password:admin

      userRepository.save(new User("Nguyen", "Binh", "thebinhnguyen2703@gmail.com",
          passwordEncoder.encode("testuser1"), Roles.USER)); // password: testuser1
      userRepository.save(new User("Nguyen", "Binh 2", "thebinhnguyen2005@gmail.com",
          passwordEncoder.encode("testuser2"), Roles.USER)); // password: testuser2

      ClassPathResource res = new ClassPathResource("/books.csv");
      try (CSVReader csvReader = new CSVReader(new InputStreamReader(res.getInputStream()))) {
        String[] columns;

        boolean isHeader = true;

        // Read CSV line by line
        while ((columns = csvReader.readNext()) != null) {
          if (isHeader) {
            isHeader = false; // Skip the header
            continue;
          }

          // Trim and create a Book object
          Book book = Book.builder()
              .bookName(columns[0].trim())
              .authors(columns[1].trim())
              .description(columns[2].trim())
              .bookCoverUrl(columns[3].trim()).bookStatus(BookStatus.AVAILABLE)
              .build();

          bookRepository.save(book);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    };
  };
}
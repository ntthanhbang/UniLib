package com.thanhbang.backend.controllers.users;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhbang.backend.entities.User;
import com.thanhbang.backend.services.JwtService;
import com.thanhbang.backend.services.SendGridMailService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user/email")
@RequiredArgsConstructor
public class SendEmailUser {
    private final JwtService jwtService;
    private final SendGridMailService sendGridMailService;

    @GetMapping("/test")
    public ResponseEntity<String> SendTestEmail(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if(authHeader!= null & authHeader.startsWith("Bearer ")){
            String jwt = authHeader.substring(7);
            User user = jwtService.getUser(jwt);
            sendGridMailService.sendMail("Test emaik", "if you see this, our email service is working", Collections.singletonList(user.getEmail()), null, null);
            return ResponseEntity.ok("email sent!");
        }
        else{
            return ResponseEntity.status(401).build();
        }
    }
}

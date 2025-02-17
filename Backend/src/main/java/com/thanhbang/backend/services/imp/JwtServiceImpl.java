package com.thanhbang.backend.services.imp;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import com.thanhbang.backend.entities.Token;
import com.thanhbang.backend.entities.User;
import com.thanhbang.backend.exceptions.TokenNotFoundException;
import com.thanhbang.backend.repositories.TokenRepository;
import com.thanhbang.backend.repositories.UsersRepositories;
import com.thanhbang.backend.services.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
  private final TokenRepository tokenRepository;
  private final UsersRepositories usersRepositories;

  @Value("${token.signing.key}")
  private String jwtSigningKey;

  @Value("${token.refresh.signing.key}")
  private String jwtRefreshSigningKey;

  @Value("${token.access.token.expiration}")
  private long accessTokenExpiration;

  @Value("${token.refresh.token.expiration}")
  private long refreshTokenExpiration;

  @Override
  public String extractUserName(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public User getUser(String token) {
    String email = extractUserName(token);
    User userData = usersRepositories.findByEmail(email).get();
    return userData;
  }

  @Override
  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails, accessTokenExpiration);
  }

  @Override
  public String generateRefreshToken(UserDetails userDetails) {
    return "REFRESH_" + generateToken(new HashMap<>(), userDetails, refreshTokenExpiration, jwtRefreshSigningKey);
  }

  @Override
  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String userName = extractUserName(token);
    Token tokenFromDb = tokenRepository.findByToken(token).orElseThrow(() -> new TokenNotFoundException(token));
    return (tokenFromDb.getIsActive() && userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
    final Claims claims = extractAllClaims(token);
    return claimsResolvers.apply(claims);
  }

  private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
    return generateToken(extraClaims, userDetails, expiration, jwtSigningKey);
  }

  private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration,
      String signingKey) {
    return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(getSigningKey(signingKey), SignatureAlgorithm.HS256).compact();
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder().setSigningKey(getSigningKey(jwtSigningKey)).build().parseClaimsJws(token)
        .getBody();
  }

  private Key getSigningKey(String key) {
    byte[] keyBytes = Decoders.BASE64.decode(key);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
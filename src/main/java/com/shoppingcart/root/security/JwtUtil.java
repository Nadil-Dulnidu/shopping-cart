package com.shoppingcart.root.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secretKey;

    public List<GrantedAuthority> validateToken(String token) {
        if (!isTokenExpired(token)) {
            final Claims claims = extractAllClaims(token);
            String role = (String) claims.get("role");
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(() -> role);
            return authorities;
        }
        return new ArrayList<>();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Optional<String> extractUsernameOpt(String token) {
        if(!isTokenExpired(token)){
            return Optional.of(extractAllClaims(token).getSubject());
        }
        return Optional.empty();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
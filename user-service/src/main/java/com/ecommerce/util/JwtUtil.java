package com.ecommerce.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "my-super-secret-key-which-is-long-123456";

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // ✅ Generate Token
    public String generateToken(String username,String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role",role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key)
                .compact();
    }
    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    // ✅ Extract Username
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // ✅ Extract Claims (FIXED)
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()   // ✅ IMPORTANT
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)   // ✅ works now
                .getBody();
    }
}

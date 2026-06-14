package trace.service.impl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import trace.service.interfaces.JwtService;

import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expiry;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    private Jws<Claims> extractAllClaims(String token) {
        return Jwts.parser() // sets token parser
                .verifyWith(getSigningKey()) // verify if its a valid token using signing key
                .build() // builds an immutable thread safe jwt parser
                .parseSignedClaims(token);  // Parse a signed JWT and verify its signature;
    }

    @Override
    public String generateToken(String email) {
        Date now = new Date();
        return Jwts.builder() // creates a builder for jwt
                .subject(email) // sets subject
                .issuedAt(now) // sets issue time
                .expiration(new Date(now.getTime() + expiry)) // sets expiry time
                .signWith(getSigningKey()) // sets signing key
                .compact(); // builds in string format
    }

    @Override
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token format: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }
        return false;
    }

    @Override
    public String extractEmail(String token) {
        Claims claims = extractAllClaims(token).getPayload();
        return claims.getSubject(); // extract subject out of payload
    }
}

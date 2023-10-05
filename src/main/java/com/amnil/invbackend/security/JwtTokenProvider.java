package com.amnil.invbackend.security;

import com.amnil.invbackend.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * The type Jwt token provider.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {

    /**
     * jwtSecret
     */
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    /**
     * jwtExpirationDate
     */
    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    /**
     * Generate token string.
     *
     * @param authentication the authentication
     * @return the string
     */
// Generate JWT token
    public String generateToken(Authentication authentication){
        String username = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();

        return token;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    /**
     * Get username string.
     *
     * @param token the token
     * @return the string
     */
// Get username from JWT token
    public String getUsername(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();

        return username;
    }

    /**
     * Validate token boolean.
     *
     * @param token the token
     * @return the boolean
     */
// Validate JWT Token
    public boolean validateToken(String token){
       try {
           Jwts.parserBuilder()
                   .setSigningKey(key())
                   .build()
                   .parse(token);
           return true;
       } catch (MalformedJwtException e){
            log.error("Invalid JWT token: {}",e.getMessage());
       }catch(ExpiredJwtException e){
            log.error("JWT token is expired: {}",e.getMessage());
       }catch (UnsupportedJwtException e){
            log.error("JWT token is unsupported: {}",e.getMessage());
       }catch (IllegalArgumentException e){
            log.error("JWT claims string is empty: {}",e.getMessage());
       }
                return false;
    }
} /**
 * log
 */

package com.finalprojectc7t3.backend.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class UtilidadJwt {

    private String SECRET_PASSWORD = "algunaClave";

    public String extractUser (String token) {
        return extractRequestedUser(token);
    }

    public Date extractExpiration(String token) {
        return extractDateRequest(token);
    }

    public Date extractDateRequest(String token){
        Claims claims = extractAllRequests(token);
        return claims.getExpiration();
    }

    public String extractRequestedUser(String token){
        Claims claims = extractAllRequests(token);
        return claims.getSubject();
    }
    private Claims extractAllRequests(String token) {
        return Jwts.parser().setSigningKey(SECRET_PASSWORD).parseClaimsJws(token).getBody();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(10).toInstant()))
                .signWith(SignatureAlgorithm.HS256, SECRET_PASSWORD).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUser(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}

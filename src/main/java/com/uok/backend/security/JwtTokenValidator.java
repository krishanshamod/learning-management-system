package com.uok.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenValidator implements TokenValidator {

    private String secret = "OurLittleSecretIsHere";

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    @Override
    public String getEmailFromToken(String token) {
        Claims claims = getClaims(token);
        return (String) claims.get("email");
    }

    @Override
    public String getFirstNameFromToken(String token) {
        Claims claims = getClaims(token);
        return (String) claims.get("firstName");
    }

    @Override
    public String getLastNameFromToken(String token) {
        Claims claims = getClaims(token);
        return (String) claims.get("lastName");
    }

    @Override
    public String getRoleFromToken(String token) {
        Claims claims = getClaims(token);
        return (String) claims.get("role");
    }

    @Override
    public Boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

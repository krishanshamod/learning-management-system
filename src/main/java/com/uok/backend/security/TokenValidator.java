package com.uok.backend.security;

public interface TokenValidator {
    public String getEmailFromToken(String token);
    public String getFirstNameFromToken(String token);
    public String getLastNameFromToken(String token);
    public String getRoleFromToken(String token);
    public Boolean validate(String token);
}

package com.sofrecom.sn3.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtServices {

    String extractUserNameFromJWT(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);

}

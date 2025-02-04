package com.sofrecom.sn3.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;
@Service
public class JwtServiceImpl implements JwtServices {
    @Value("${token.signing.key.local}")
    private String jwtSigningKey;

    @Override
    public String extractUserNameFromJWT(String token) {
        return getClaimFromJWT(token,Claims::getSubject);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().setClaims(new HashMap<>()).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24*3))//3days
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUserNameFromJWT(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    @Override
    public boolean isTokenExpired(String token) {
        Date expiration = getClaimFromJWT(token, Claims::getExpiration);
        if (expiration.after(new Date(System.currentTimeMillis()))) {
            return false;
        }
        else return true;
    }
    private Key getSigningKey() {
        byte[] keyBytes= Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private <T> T getClaimFromJWT(String token, Function<Claims, T> claimsResolver) {
        final Claims claims=Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }
}

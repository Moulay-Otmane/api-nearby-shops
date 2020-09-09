package com.codingchallenge.api_nearby_shops.security.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -712677574281318564L;

    @Value("${jwt.secret}")
    private String secret;
    private static final long TOKEN_VALIDITY = 60 * 60 * 9;

    // retrieve username from jwt token
    public String extractUsernameFromToken(String token){
        return extractClaimFromToken(token, Claims::getSubject);
    }

    // retrieve expiration date of jwt token from the token it self
    public Date extractExpirationDateFromToken(String token){
        return extractClaimFromToken(token, Claims::getExpiration);
    }

    // generate token for user
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    // retrieve specific claim form token
    private <T> T extractClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // validate token
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())) && !(isTokenExpired(token));
    }

    // retrieve all the claims from the token
    private Claims extractAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token){
        Date expiration = extractExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /*    Generate token by define the claims (Expiration, Subject, and the ID)
        and sign the token using the HS512 algorithm and secret key.*/
    private String doGenerateToken(Map<String, Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

}

package com.ntnt.chatapp.services;

import com.ntnt.chatapp.models.CustomUserDetails;
import com.ntnt.chatapp.models.JwtResponse;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private Long EXPIRATION;

    @Value("${jwt.prefix}")
    private String prefix;

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class.getName());

    public String generateToken(Authentication authentication){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<>();

        claims.put("name", userDetails.getName());
        claims.put("authorities", userDetails.getAuthorities());

        return Jwts.builder()
                        .setSubject(userDetails.getUsername())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(new Date().getTime() + EXPIRATION *1000))
                        .setClaims(claims)
                        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                        .compact();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {0} ", e);

        } catch (MalformedJwtException e) {

            logger.error("Invalid JWT token -> Message: {0}", e);

        } catch (ExpiredJwtException e) {

            logger.error("Expired JWT token -> Message: {0}", e);

        } catch (UnsupportedJwtException e) {

            logger.error("Unsupported JWT token -> Message: {0}", e);

        } catch (IllegalArgumentException e) {

            logger.error("JWT claims string is empty -> Message: {0}", e);

        }
        return false;
    }

    public JwtResponse generateJwtResponse(Authentication authentication){
        String token = generateToken(authentication);
        return new JwtResponse(token, prefix);
    }

    public UserDetails getUserDetailsFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                                        .parseClaimsJws(token)
                                        .getBody();

        try{
            String username = claims.getSubject();

            return new CustomUserDetails(username, null, null);

        }catch (Exception e){
            logger.error("Cannot get user info from token -> Message: {0}", e);
        }
        return null;
    }
}

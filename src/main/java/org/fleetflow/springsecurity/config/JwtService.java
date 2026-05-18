package org.fleetflow.springsecurity.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtService {

    private String secret_key="votre_secret_key_avec_au_moins_32_caracteres_pour_HS256";


    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 15))
                .signWith(SignatureAlgorithm.HS256,secret_key.getBytes())
                .compact();
    }

    public String extractEmail(String token){
        return  Jwts.parser()
                .setSigningKey(secret_key.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public Boolean validateToken(String token,String email){
        return extractEmail(token).equals(email) && !isTokenExpired(token);
    }
    public Boolean isTokenExpired(String token){
        return Jwts.parser()
                .setSigningKey(secret_key.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

}

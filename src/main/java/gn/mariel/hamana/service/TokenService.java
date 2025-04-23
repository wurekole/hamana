package gn.mariel.hamana.service;

import gn.mariel.hamana.dto.TokenInputDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;


import java.util.Map;

@Service
public class TokenService {

    public String generateJwt(TokenInputDto inputs, Map<String, String> claims) {

        String jwt = Jwts.builder()
                .setSubject(inputs.getSubject())    // Add custom claims, such as subject// More custom claims if needed
                .claims(claims)
                .setIssuer(inputs.getIssuer())// Set issuer
                .setIssuedAt(new java.util.Date()) // Set issued time
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 3600000)) // Set expiration time (1 hour from now)
                .signWith(SignatureAlgorithm.HS256, "secretkeyrewrerererererererevreereererrrrrrrrrrrrrrrrrrrrrrrrrrrrrererererfrev")  // Signing algorithm and secret
                .compact();
        return jwt;
    }

    public String getClaim(String token,String claimName){
        Claims claims = getClaims(token);
        String claim = claims.get(claimName,String.class);
        return claim;
    }

    public String getSubject(String token){
        var claims = getClaims(token);
        return claims.getSubject();
    }

    private Claims getClaims(String token){
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey("secretkeyrewrerererererererevreereererrrrrrrrrrrrrrrrrrrrrrrrrrrrrererererfrev")
                .build()
                .parseClaimsJws(token);

        Claims claims = jws.getBody();
        return claims;
    }
}

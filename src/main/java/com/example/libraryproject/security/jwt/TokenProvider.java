package com.example.libraryproject.security.jwt;


import com.example.libraryproject.model.dao.entity.User;
import com.example.libraryproject.security.SecurityProperties;
import com.example.libraryproject.utils.PublicPrivateKeyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.libraryproject.constant.TokenConstants.EMAIL_KEY;

@Component
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenProvider implements TokenService<User, Claims> {
    final SecurityProperties securityProperties;

    @Override
    public List<String> generate(User obj) {
        List<String> tokens = new ArrayList<>();
        tokens.add(0, generateAccessToken(obj));
        tokens.add(1, generateRefreshToken(obj));
        return tokens;
    }

    @Override
    public Claims read(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(PublicPrivateKeyUtils.getPublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public String getEmail(String token) {
        return read(token).get(EMAIL_KEY, String.class);
    }

    private String generateAccessToken(User user) {
        Claims claims = Jwts.claims();
        claims.put(EMAIL_KEY, user.getEmail());

        Date now = new Date();
        Date exp = new Date(now.getTime() + securityProperties.getJwt().getAccessTokenValidityTime());

        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setIssuedAt(now)
                .setExpiration(exp)
                .addClaims(claims)
                .signWith(PublicPrivateKeyUtils.getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    private String generateRefreshToken(User user) {
        Claims claims = Jwts.claims();
        claims.put(EMAIL_KEY, user.getEmail());

        Date now = new Date();
        Date exp = new Date(now.getTime() + securityProperties.getJwt().getRefreshTokenValidityTime());

        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setIssuedAt(now)
                .setExpiration(exp)
                .addClaims(claims)
                .signWith(PublicPrivateKeyUtils.getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

}

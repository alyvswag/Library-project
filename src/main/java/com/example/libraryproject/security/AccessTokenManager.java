package com.example.libraryproject.security;

import com.example.libraryproject.model.dao.User;
import com.example.libraryproject.security.base.EmailGetter;
import com.example.libraryproject.security.base.TokenGenerator;
import com.example.libraryproject.security.base.TokenReader;
import com.example.libraryproject.security.models.SecurityJwtData;
import com.example.libraryproject.security.models.SecurityProperties;
import com.example.libraryproject.security.utils.PublicPrivateKeyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

import static com.example.libraryproject.constant.TokenConstants.EMAIL_KEY;

@Component
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AccessTokenManager implements TokenGenerator<User>, TokenReader<Claims>, EmailGetter {

    final SecurityProperties securityProperties;

    @Override
    public String generate(User obj) {

        Claims claims = Jwts.claims();
        claims.put(EMAIL_KEY, obj.getEmail());

        Date now = new Date();
        Date exp = new Date(now.getTime() + securityProperties.getJwt().getAccessTokenValidityTime());

        return Jwts.builder()
                .setSubject(String.valueOf(obj.getId()))
                .setIssuedAt(now)
                .setExpiration(exp)
                .addClaims(claims)
                .signWith(PublicPrivateKeyUtils.getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    @Override
    public Claims read(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(PublicPrivateKeyUtils.getPublicKey())
                .build()
                .parseClaimsJws(token)//her gelen token parse olunmur yanliz bize mexsus olan verify olanlar
                .getBody();
    }

    @Override
    public String getEmail(String token) {
        return read(token).get(EMAIL_KEY,String.class);
    }
}

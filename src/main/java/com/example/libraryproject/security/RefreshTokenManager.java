package com.example.libraryproject.security;

import com.example.libraryproject.model.dao.User;
import com.example.libraryproject.security.base.EmailGetter;
import com.example.libraryproject.security.base.TokenGenerator;
import com.example.libraryproject.security.base.TokenReader;
import com.example.libraryproject.security.models.SecurityProperties;
import com.example.libraryproject.security.models.dto.RefreshTokenDto;
import com.example.libraryproject.security.utils.PublicPrivateKeyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.example.libraryproject.constant.TokenConstants.EMAIL_KEY;

@Component
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshTokenManager implements TokenGenerator<RefreshTokenDto>, TokenReader<Claims>, EmailGetter {

    final SecurityProperties securityProperties;

    @Override
    public String generate(RefreshTokenDto obj) {
        final User user = obj.getUser();

        Claims claims = Jwts.claims();
        claims.put(EMAIL_KEY, user.getEmail());
        claims.put("type", "REFRESH_TOKEN");

        Date now = new Date();
        Date exp = new Date(now.getTime() + securityProperties.getJwt().getRefreshTokenValidityTime(obj.isRememberMe()));

        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setIssuedAt(now)
                .setExpiration(exp)
                .addClaims(claims)
                .signWith(PublicPrivateKeyUtils.getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    @Override
    public Claims read(String token) {
        Claims tokenData = Jwts.parserBuilder()
                .setSigningKey(PublicPrivateKeyUtils.getPublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String typeOfToken = tokenData.get("type", String.class);

        if (!"REFRESH_TOKEN".equals(typeOfToken)) {
            throw new RuntimeException("Invalid type of token");//todo:BaseException
        }

        return tokenData;
    }

    @Override
    public String getEmail(String token) {
        return read(token).get(EMAIL_KEY, String.class);
    }
}

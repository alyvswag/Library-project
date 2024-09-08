package com.example.libraryproject.security.filters;

import com.example.libraryproject.redis.RedisService;
import com.example.libraryproject.security.AccessTokenManager;
import com.example.libraryproject.security.RefreshTokenManager;
import com.example.libraryproject.service.auth.AuthService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.example.libraryproject.constant.TokenConstants.EMAIL_KEY;
import static com.example.libraryproject.constant.TokenConstants.PREFIX;

@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

    final AccessTokenManager accessTokenManager;
    final UserDetailsService userDetailsService;
    final AuthService authService;
    final RedisService redisService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null && token.startsWith(PREFIX)) {
//            redisService.exists(token.substring(7));
            authService.setAuthentication(
                    accessTokenManager.getEmail(
                            token.substring(7)));
        }
        filterChain.doFilter(request, response);//do filter olmazsa sorgu gelmez
    }
}

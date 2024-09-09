package com.example.libraryproject.security.filters;

import com.example.libraryproject.service.redis.RedisService;
import com.example.libraryproject.security.jwt.TokenProvider;
import com.example.libraryproject.service.auth.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.example.libraryproject.constant.TokenConstants.PREFIX;

@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

    final TokenProvider tokenProvider;
    final UserDetailsService userDetailsService;
    final AuthService authService;
    final RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenRequest = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (tokenRequest != null && tokenRequest.startsWith(PREFIX)) {
            String token = tokenRequest.substring(7);
            if (redisService.exists(token)) {
                authService.setAuthentication(
                        tokenProvider.getEmail(token));
            }
        }
        filterChain.doFilter(request, response);//do filter olmazsa sorgu gelmez
    }
}

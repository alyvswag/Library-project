package com.example.libraryproject.security.config;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.security.filters.AuthorizationFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

import static com.example.libraryproject.model.enums.response.ErrorResponseMessages.FORBIDDEN;


@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                         PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AuthorizationFilter authorizationFilter) throws Exception {
        return http
                .authorizeHttpRequests(request -> {
                            // Swagger UI
                            request.requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll();

                            //lazimmsiz urller
                            request.requestMatchers("/api/v1/user/**").permitAll();
                            request.requestMatchers("/api/v1/book/get-book-by-id/**").hasRole("ADMIN");
                            request.requestMatchers("/api/v1/book/get-all-books/**").hasRole("USER");
                            request.requestMatchers("/test/test").anonymous();
                            request.requestMatchers("/test/test1").authenticated();

                            // Auth URLs
                            request.requestMatchers("/v1/auth/logout").authenticated();
                            request.requestMatchers("api/v1/auth/**").anonymous();
                        }
                )
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling(eh -> eh.authenticationEntryPoint(authEntryPoint))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

    }

//    @Component
//    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
//    @Slf4j
//    @Primary
//    public static class AuthEntryPoint implements AuthenticationEntryPoint {
//
//        @Qualifier("handlerExceptionResolver")
//        final HandlerExceptionResolver resolver;
//
//        @Override
//        public void commence(HttpServletRequest request,
//                             HttpServletResponse response,
//                             AuthenticationException authException) throws IOException, ServletException {
//
//            authException.printStackTrace();
//            resolver.resolveException(request, response, null, BaseException.of(FORBIDDEN));
//        }
//    }

}

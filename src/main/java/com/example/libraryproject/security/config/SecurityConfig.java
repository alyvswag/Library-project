package com.example.libraryproject.security.config;

import com.example.libraryproject.security.filters.AuthorizationFilter;
import org.springdoc.webmvc.core.service.RequestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


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
                                                   AuthorizationFilter authorizationFilter,
                                                   // AuthEntryPoint authEntryPoint
                                                   RequestService requestBuilder)
            throws Exception {
        return http
                .authorizeHttpRequests(request -> {
                            // Swagger UI
                            request.requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll();

                            //lazimmsiz urller
                            request.requestMatchers("/api/v1/user/**").permitAll();
                            request.requestMatchers("/api/v1/book/**").authenticated();
                            request.requestMatchers("/test/test").anonymous();
                            request.requestMatchers("/test/test1").authenticated();


                            // Auth URLs
                            request.requestMatchers("/v1/auth/logout").authenticated();
                            request.requestMatchers("api/v1/auth/**").anonymous();
                        }
                )
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

    }

}

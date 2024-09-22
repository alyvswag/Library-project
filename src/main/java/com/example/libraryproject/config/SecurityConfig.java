package com.example.libraryproject.config;

import com.example.libraryproject.security.filters.AuthorizationFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import static com.example.libraryproject.constant.SecurityPathConstants.*;


@Configuration
@RequiredArgsConstructor
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
        http
                .authorizeHttpRequests(request -> {
                    //PERMIT ALL LIST FOR TEST
                    request.requestMatchers(TEST_PERMIT_ALL).permitAll();
                    //PERMIT ALL LIST
                    request.requestMatchers(PERMIT_ALL_LIST).permitAll();
//                    //AUTHENTICATED
//                    request.requestMatchers(AUTHENTICATED).authenticated();
//                    //HAS_ROLE_SUPER_ADMIN
//                    request.requestMatchers(HAS_ROLE_SUPER_ADMIN).hasRole("SUPERADMIN");
//                    //HAS_ANY_ROLE_SUPER_ADMIN_AND_ADMIN
//                    request.requestMatchers(HAS_ANY_ROLE_SUPER_ADMIN_AND_ADMIN).hasAnyRole("SUPERADMIN", "ADMIN");
//                    //HAS_ROLE_USER
//                    request.requestMatchers(HAS_ROLE_USER).hasRole("USER");
                })
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(eh -> {
                    eh.authenticationEntryPoint(authenticationEntryPoint());
                    eh.accessDeniedHandler(customAccessDeniedHandler());
                })
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }


    // authentrypoint,accessdeniedhandler
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {

        return (request, response, authException) -> {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().println("{ \"error\": \"" + authException.getMessage() + "\" }");
        };
    }

    @Bean
    public AccessDeniedHandler customAccessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getOutputStream().println("{ \"error\": \"" + accessDeniedException.getMessage() + "\" }");
        };
    }

}

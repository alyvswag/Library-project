package com.example.libraryproject.constant;

public final class SecurityPathConstants {
    public static final String[] PERMIT_ALL_LIST = {
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/v1/auth/register-user",
            "/api/v1/auth/login",
            "/api/v1/auth/refreshToken/**"
    };
    public static final String[] ANONYMOUS  = {
            "/swagger-ui.html",
    };
    public static final String[] AUTHENTICATED  = {
            "/swagger-ui.html",
    };
    public static final String[] HAS_ROLE_SUPER_ADMIN = {
            "/api/v1/**",
    };
    public static final String[] HAS_ROLE_ADMIN = {
            "/swagger-ui.html",
    };
    public static final String[] HAS_ROLE_USER= {
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/v1/auth/register-user",
            "/api/v1/auth/login",
            "/api/v1/auth/refreshToken/**"
    };
}

package com.example.libraryproject;

import com.example.libraryproject.mapper.user.UserMapper;
import com.example.libraryproject.model.dao.Role;
import com.example.libraryproject.model.dao.User;
import com.example.libraryproject.model.dto.request.login.LoginRequestPayload;
import com.example.libraryproject.model.dto.response.login.LoginResponse;
import com.example.libraryproject.model.dto.response.payload.UserResponse;
import com.example.libraryproject.model.enums.user.RoleName;
import com.example.libraryproject.repository.user.RoleRepository;
import com.example.libraryproject.repository.user.UserRepository;
import com.example.libraryproject.security.SecurityJwtData;
import com.example.libraryproject.security.SecurityProperties;
import com.example.libraryproject.security.jwt.TokenProvider;
import com.example.libraryproject.service.auth.AuthServiceImpl;
import com.example.libraryproject.service.redis.RedisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.context.SecurityContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.libraryproject.model.enums.user.RoleName.ADMIN;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    UserRepository userRepository;
    @Mock
    TokenProvider tokenProvider;
    @Mock
    UserDetailsService userDetailsService;
    @Mock
    RedisService redisService;
    @Mock
    SecurityProperties securityProperties;
    @Mock
    UserMapper userMapper;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    RoleRepository roleRepository;

    @Mock
    private SecurityContext securityContext;
    @Mock
    SecurityJwtData securityJwtData;

    @InjectMocks
    AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);

        when(securityProperties.getJwt()).thenReturn(securityJwtData);
        when(securityJwtData.getAccessTokenValidityTime()).thenReturn(3600L); // 1 saat
        when(securityJwtData.getRefreshTokenValidityTime()).thenReturn(7200L); // 2 saat
        when(securityJwtData.getPublicKey()).thenReturn("publickey");
        when(securityJwtData.getPrivateKey()).thenReturn("privatekey");
    }


    @Test
    void testSetAuthentication() {
        // Mock verilənlər
        String email = "test@example.com";
        UserDetails userDetails = mock(UserDetails.class); // UserDetails obyektini mock edirik

        // Mock davranışlar
        when(userDetailsService.loadUserByUsername(email)).thenReturn(userDetails);
        when(userDetails.getAuthorities()).thenReturn(null); // authorities mock edirik

        // Test edilən metodun çağırılması
        authService.setAuthentication(email);

        // Doğrulama
        verify(userDetailsService, times(1)).loadUserByUsername(email); // loadUserByUsername metodunun bir dəfə çağırıldığını təsdiqləyirik
        verify(securityContext, times(1)).setAuthentication(any(UsernamePasswordAuthenticationToken.class)); // setAuthentication metodunun çağırıldığını təsdiqləyirik
    }
}

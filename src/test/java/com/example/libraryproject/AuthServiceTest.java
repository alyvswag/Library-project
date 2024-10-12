package com.example.libraryproject;

import com.example.libraryproject.mapper.user.UserMapper;
import com.example.libraryproject.model.dao.entity.Role;
import com.example.libraryproject.model.dao.entity.User;
import com.example.libraryproject.model.dto.request.create.UserRequestCreate;
import com.example.libraryproject.model.dto.request.login.LoginRequestPayload;
import com.example.libraryproject.model.dto.response.login.LoginResponse;
import com.example.libraryproject.model.dto.response.payload.UserResponse;
import com.example.libraryproject.repository.user.RoleRepository;
import com.example.libraryproject.repository.user.UserRepository;
import com.example.libraryproject.security.SecurityJwtData;
import com.example.libraryproject.security.SecurityProperties;
import com.example.libraryproject.security.jwt.TokenProvider;
import com.example.libraryproject.service.auth.AuthServiceImpl;
import com.example.libraryproject.service.redis.RedisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.context.SecurityContext;

import java.util.List;
import java.util.Optional;

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
    Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private User userMock;

    @InjectMocks
    AuthServiceImpl authService;

    @Mock
    private Role role;
    @Mock
    private UserRequestCreate userRequestCreate;
    private LoginRequestPayload loginRequestPayload;
    private User user;
    SecurityJwtData securityJwtData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);

        loginRequestPayload = new LoginRequestPayload();
        loginRequestPayload.setEmail("test@example.com");
        loginRequestPayload.setPassword("password");

        user = new User();
        user.setEmail("test@example.com");
        user.setName("Test");
        user.setSurname("User");

        securityJwtData = new SecurityJwtData();
        securityJwtData.setAccessTokenValidityTime(3600000L);
        securityJwtData.setRefreshTokenValidityTime(86400000L);

        when(securityProperties.getJwt()).thenReturn(securityJwtData);
    }

    @Test
    void loginTest() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userRepository.findUserByEmail(loginRequestPayload.getEmail())).thenReturn(Optional.of(user));
        when(tokenProvider.generate(any(User.class))).thenReturn(List.of("access-token", "refresh-token"));
        when(userMapper.toDto(any(User.class))).thenReturn(new UserResponse());

        LoginResponse loginResponse = authService.login(loginRequestPayload);
        assertNotNull(loginResponse);//null olmamasin yoxlayiram
        assertEquals("access-token", loginResponse.getAccessToken());//generate elediyim tokenle ust uste dusub dusmemesin yoxayiram
        assertEquals("refresh-token", loginResponse.getRefreshToken());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(redisService, times(1)).set(eq("access-token"), anyString(), eq(3600000L));
        verify(redisService, times(1)).set(eq("refresh-token"), anyString(), eq(86400000L));
    }


    @Test
    void testSetAuthentication() {
        String email = "test@example.com";
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername(email)).thenReturn(userDetails);
        when(userDetails.getAuthorities()).thenReturn(null);
        authService.setAuthentication(email);
        verify(userDetailsService, times(1)).loadUserByUsername(email);
        verify(securityContext, times(1)).setAuthentication(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void refreshTokenTest() {
        String refreshToken = "refresh-token";
        String email = "test@example.com";
        when(tokenProvider.getEmail(refreshToken)).thenReturn(email);
        when(redisService.isTokenMine(email, refreshToken)).thenReturn(true);
        when(userRepository.findUserByEmail(loginRequestPayload.getEmail())).thenReturn(Optional.of(user));
        when(tokenProvider.generate(any(User.class))).thenReturn(List.of("access-token", "refresh-token"));
        when(userMapper.toDto(any(User.class))).thenReturn(new UserResponse());
        LoginResponse loginResponse = authService.refreshToken(refreshToken);
        assertNotNull(loginResponse);
        assertEquals("access-token", loginResponse.getAccessToken());
        assertEquals("refresh-token", loginResponse.getRefreshToken());
        verify(redisService, times(1)).set(eq("access-token"), anyString(), eq(3600000L));
        verify(redisService, times(1)).set(eq("refresh-token"), anyString(), eq(86400000L));
    }

    @Test
    void logoutTest() {
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("test@example.com");

        doNothing().when(redisService).delete(anyString());
        doNothing().when(redisService).deleteMap(anyString());
        authService.logout();
        verify(redisService).delete(redisService.getAccessTokenForEmail("test@example.com"));
        verify(redisService).delete(redisService.getRefreshTokenForEmail("test@example.com"));
        verify(redisService).deleteMap("test@example.com");

    }

}

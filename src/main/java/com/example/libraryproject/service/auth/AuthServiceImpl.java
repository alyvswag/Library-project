package com.example.libraryproject.service.auth;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.user.UserMapper;
import com.example.libraryproject.model.dao.Role;
import com.example.libraryproject.model.dao.User;
import com.example.libraryproject.model.dto.request.create.UserRequestCreate;
import com.example.libraryproject.model.enums.user.RoleName;
import com.example.libraryproject.redis.RedisService;
import com.example.libraryproject.repository.user.RoleRepository;
import com.example.libraryproject.repository.user.UserRepository;
import com.example.libraryproject.security.config.SecurityConfig;
import com.example.libraryproject.security.models.SecurityProperties;
import com.example.libraryproject.security.models.jwt.TokenProvider;
import com.example.libraryproject.security.models.login.LoginRequestPayload;
import com.example.libraryproject.security.models.response.LoginResponse;
import com.example.libraryproject.utils.CommonUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.libraryproject.constant.TokenConstants.*;
import static com.example.libraryproject.model.enums.response.ErrorResponseMessages.*;
import static com.example.libraryproject.model.enums.user.RoleName.USER;
import static com.example.libraryproject.utils.CommonUtils.isValidEmailAddress;
import static com.example.libraryproject.utils.CommonUtils.throwIf;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    final AuthenticationManager authenticationManager;
    final UserRepository userRepository;
    final TokenProvider tokenProvider;
    final UserDetailsService userDetailsService;
    final RedisService redisService;
    final SecurityProperties securityProperties;
    final UserMapper userMapper;
    final PasswordEncoder passwordEncoder;
    final RoleRepository roleRepository;

    @Override
    public LoginResponse login(LoginRequestPayload loginRequestPayload) {
        authenticate(loginRequestPayload);
        return prepareLoginResponse(loginRequestPayload.getEmail());
    }

    @Override
    public LoginResponse refreshToken(String refreshToken) {
        String email = tokenProvider.getEmail(refreshToken);
        throwIf(
                ()-> !redisService.isTokenMine(email, refreshToken),
                BaseException.of(WRONG_TOKEN)
        );
        redisService.deleteMap(email);
        return prepareLoginResponse(tokenProvider.getEmail(refreshToken));
    }

    @Override
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        redisService.delete(redisService.getAccessTokenForEmail(authentication.getName()));
        redisService.delete(redisService.getRefreshTokenForEmail(authentication.getName()));
        SecurityContextHolder.clearContext();
    }

    @Override
    public LoginResponse registerUser(UserRequestCreate userRequestCreate) {
        throwIf(
                () -> !isValidEmailAddress(userRequestCreate.getEmail()),
                BaseException.of(INVALID_EMAIL_FORMAT)
        );
        throwIf(
                () -> userRepository.findUserByEmail(userRequestCreate.getEmail()).isPresent(),
                BaseException.of(EMAIL_ALREADY_REGISTERED)
        );
        User user = userMapper.toEntity(userRequestCreate);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findRole(USER));
        user.setRoles(roles);
        userRepository.save(user);
        return prepareLoginResponse(userRequestCreate.getEmail());
    }

    @Override
    public void setAuthentication(String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
        );

    }


    //private

    private void authenticate(LoginRequestPayload request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw e.getCause() instanceof BaseException ?
                    (BaseException) e.getCause() :
                    BaseException.unexpected();
        }
    }

    private LoginResponse prepareLoginResponse(String email) {

        User user = findUserByEmail(email);
        String accessToken = tokenProvider.generate(user).get(0);
        String refreshToken = tokenProvider.generate(user).get(1);
        setAccessTokenRedisDb(accessToken, email);
        setRefreshTokenRedisDb(refreshToken, email);
        addTokensToEmail(email, accessToken, refreshToken);
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }

    private User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(
                        () -> BaseException.notFound(User.class.getSimpleName(), "user", email)
                );
    }

    private void setAccessTokenRedisDb(String accessToken, String email) {
        redisService.set(accessToken, ACCESS_TOKEN + accessToken, securityProperties.getJwt().getAccessTokenValidityTime());
    }

    private void setRefreshTokenRedisDb(String refreshToken, String email) {
        redisService.set(refreshToken, REFRESH_TOKEN + refreshToken, securityProperties.getJwt().getRefreshTokenValidityTime());
    }

    private void addTokensToEmail(String email, String accessToken, String refreshToken) {
        redisService.addTokensToEmail(email, accessToken, refreshToken);
    }

}

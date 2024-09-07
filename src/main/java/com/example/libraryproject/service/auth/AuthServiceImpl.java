package com.example.libraryproject.service.auth;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.model.dao.User;
import com.example.libraryproject.model.dto.request.create.UserRequestCreate;
import com.example.libraryproject.repository.user.UserRepository;
import com.example.libraryproject.security.AccessTokenManager;
import com.example.libraryproject.security.RefreshTokenManager;
import com.example.libraryproject.security.models.dto.RefreshTokenDto;
import com.example.libraryproject.security.models.login.LoginRequestPayload;
import com.example.libraryproject.security.models.login.RefreshTokenRequestPayload;
import com.example.libraryproject.security.models.repsonse.LoginResponse;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import static com.example.libraryproject.constant.TokenConstants.EMAIL_KEY;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    final AuthenticationManager authenticationManager;
    final UserRepository userRepository;
    final AccessTokenManager accessTokenManager;
    final RefreshTokenManager refreshTokenManager;
    final UserDetailsService userDetailsService;

    @Override
    public LoginResponse login(LoginRequestPayload loginRequestPayload) {
        authenticate(loginRequestPayload);

        return prepareLoginResponse(loginRequestPayload.getEmail(), loginRequestPayload.getRememberMe());

    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequestPayload refreshToken) {
        return prepareLoginResponse(refreshTokenManager.getEmail(refreshToken.getRefreshToken()), refreshToken.getRememberMe());
    }

    @Override
    public void logout() {

    }
    @Override
    public void registerUser(UserRequestCreate userRequestCreate) {

    }
    @Override
    public void setAuthentication(String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities())
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

    private LoginResponse prepareLoginResponse(String email, Boolean rememberMe) {

        User user = findUserByEmail(email);

        return LoginResponse.builder()
                .accessToken(accessTokenManager.generate(user))
                .refreshToken(refreshTokenManager.generate(
                        RefreshTokenDto.builder()
                                .user(user)
                                .rememberMe(rememberMe)
                                .build()
                ))
                .build();

    }

    private User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(
                        () -> BaseException.notFound(User.class.getSimpleName(), "user", email)
                );
    }
}

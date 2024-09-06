package com.example.libraryproject;

import com.example.libraryproject.model.dao.User;
import com.example.libraryproject.repository.user.UserRepository;
import com.example.libraryproject.security.AccessTokenManager;
import com.example.libraryproject.security.RefreshTokenManager;
import com.example.libraryproject.security.models.dto.RefreshTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.libraryproject.constant.TokenConstants.EMAIL_KEY;


@RequiredArgsConstructor
@SpringBootApplication
public class LibraryProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LibraryProjectApplication.class, args);
    }

    private final UserRepository userRepository;
    private final AccessTokenManager accessTokenManager;
    private final RefreshTokenManager refreshTokenManager;

    @Override
    public void run(String... args) throws Exception {
        User user = userRepository.findUserById(1L).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        RefreshTokenDto refreshTokenDto = RefreshTokenDto.builder()
                .rememberMe(true)
                .user(user)
                .build();
        String accesToken =accessTokenManager.generate(user);
        String refreshToken =refreshTokenManager.generate(refreshTokenDto);
//        System.out.println(refreshTokenManager.read(refreshToken).get(EMAIL_KEY,String.class));
//        System.out.println(accessTokenManager.read(accesToken).get(EMAIL_KEY,String.class));
    }
}
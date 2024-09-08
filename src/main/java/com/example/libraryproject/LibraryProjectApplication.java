package com.example.libraryproject;

import com.example.libraryproject.model.dao.User;
import com.example.libraryproject.repository.user.UserRepository;
import com.example.libraryproject.security.AccessTokenManager;
import com.example.libraryproject.security.RefreshTokenManager;
import com.example.libraryproject.security.models.dto.RefreshTokenDto;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.example.libraryproject.constant.TokenConstants.ACCESS_TOKEN;
import static com.example.libraryproject.constant.TokenConstants.EMAIL_KEY;


@RequiredArgsConstructor
@SpringBootApplication
public class LibraryProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LibraryProjectApplication.class, args);
    }


    @Autowired
    private RedissonClient redisson;

    @Override
    public void run(String... args) throws Exception {
        String email ="talbelyev123@gmail.com";
        System.out.println(ACCESS_TOKEN+email);
//        refreshToken.set("tako123", Duration.of(10, ChronoUnit.SECONDS));
//        refreshToken.set("tako12345", Duration.of(10, ChronoUnit.SECONDS));
    }

    //todo: baslangic

//    private final UserRepository userRepository;
//    private final AccessTokenManager accessTokenManager;
//    private final RefreshTokenManager refreshTokenManager;


//    @Override
//    public void run(String... args) throws Exception {
//        User user = userRepository.findUserById(1L).orElseThrow(
//                () -> new RuntimeException("User not found")
//        );
//
//        RefreshTokenDto refreshTokenDto = RefreshTokenDto.builder()
//                .rememberMe(true)
//                .user(user)
//                .build();
//        String accessToken =accessTokenManager.generate(user);
//        String refreshToken =refreshTokenManager.generate(refreshTokenDto);
//        System.out.println(accessToken);
//        System.out.println(refreshTokenManager.read(refreshToken).get(EMAIL_KEY,String.class));
//        System.out.println(accessTokenManager.read(accessToken).get(EMAIL_KEY,String.class));


    //todo: son

}
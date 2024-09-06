package com.example.libraryproject.security.service;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.model.dao.User;
import com.example.libraryproject.repository.user.UserRepository;
import com.example.libraryproject.security.models.LoggedInUserDetails;
import com.example.libraryproject.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDetailsServiceImpl implements UserDetailsService {

    final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username)
                .orElseThrow(
                        () -> BaseException.notFound(User.class.getSimpleName(), "user", username)
                );
        return new LoggedInUserDetails(user.getEmail(),user.getPassword(),new ArrayList<>());
    }
}

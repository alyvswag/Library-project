package com.example.libraryproject.security.service;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.model.dao.User;
import com.example.libraryproject.model.dao.UserRole;
import com.example.libraryproject.model.enums.user.RoleName;
import com.example.libraryproject.repository.user.UserRepository;
import com.example.libraryproject.repository.user.UserRoleRepository;
import com.example.libraryproject.security.models.UserPrincipal;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDetailsServiceImpl implements UserDetailsService {

    final UserRepository userRepository;
    final UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByEmail(username);
        List<UserRole> userRoles = userRoleRepository.findRolesByUserId(user.getId());
        List<String> authorities = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            authorities.add(String.valueOf(userRole.getRole().getRoleName()));
        }
        return new UserPrincipal(user.getId(), user.getEmail(), user.getPassword(), authorities);
    }

    private User findUserByEmail(String email) {
       return  userRepository.findUserByEmail(email)
                .orElseThrow(
                        () -> BaseException.notFound(User.class.getSimpleName(), "user", email)
                );
    }
}

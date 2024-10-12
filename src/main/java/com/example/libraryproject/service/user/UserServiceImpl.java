package com.example.libraryproject.service.user;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.user.UserMapper;
import com.example.libraryproject.model.dao.entity.Role;
import com.example.libraryproject.model.dao.entity.User;
import com.example.libraryproject.model.dao.entity.UserRole;
import com.example.libraryproject.model.dto.request.create.AdminRequestCreate;
import com.example.libraryproject.model.dto.request.update.UserRequestUpdate;
import com.example.libraryproject.model.dto.response.payload.UserResponse;
import com.example.libraryproject.model.enums.user.RoleName;
import com.example.libraryproject.service.email.EmailProducer;
import com.example.libraryproject.repository.user.RoleRepository;
import com.example.libraryproject.repository.user.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.libraryproject.model.enums.response.ErrorResponseMessages.EMAIL_ALREADY_REGISTERED;
import static com.example.libraryproject.model.enums.response.ErrorResponseMessages.INVALID_EMAIL_FORMAT;
import static com.example.libraryproject.model.enums.base.Status.DELETED;
import static com.example.libraryproject.model.enums.base.Status.INACTIVE;

import static com.example.libraryproject.utils.CommonUtils.*;
import static com.example.libraryproject.utils.PasswordGeneratorUtils.generatePassword;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final UserMapper userMapper;
    final EmailProducer emailProducer;
    final PasswordEncoder passwordEncoder;

    @Override
    public void addUser(AdminRequestCreate adminRequestCreate, RoleName roleName) {
        throwIf(
                () -> !isValidEmailAddress(adminRequestCreate.getEmail()),
                BaseException.of(INVALID_EMAIL_FORMAT)
        );
        throwIf(
                () -> userRepository.findUserByEmail(adminRequestCreate.getEmail()).isPresent(),
                BaseException.of(EMAIL_ALREADY_REGISTERED)
        );
        User user = userMapper.toEntity(adminRequestCreate);
        String password = generatePassword();
        emailProducer.sendEmailMessage(user.getEmail(), password);
        user.setPassword(passwordEncoder.encode(password));// todo: security tetbiq ederken passwordu encode edib bazaya yaz
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findRole(roleName));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, UserRequestUpdate userRequestUpdate) {
        User userEntity = findUserById(id);
        userMapper.updateUserFromDto(userRequestUpdate, userEntity);
        userEntity.setPassword(passwordEncoder.encode(userRequestUpdate.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        User userEntity = findUserById(id);
        userEntity.setStatus(DELETED);
        userRepository.save(userEntity);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User userEntity = findUserById(id);
        return userMapper.toDto(userEntity);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userMapper.toDto(userRepository.findAllUser());
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        User userEntity = userRepository.findUserByEmail(email)
                .orElseThrow(
                        () -> BaseException.notFound(User.class.getSimpleName(), "user", email)
                );
        return userMapper.toDto(userEntity);
    }

    @Override
    public List<UserResponse> getUsersByRole(RoleName roleName) {
        return userMapper.toDto(mapUserRoleToUserResponse(userRepository.getUsersByRole(roleName)));
    }

    @Override
    public void deactivateUser(Long id) {
        User userEntity = findUserById(id);
        userEntity.setStatus(INACTIVE);
        userRepository.save(userEntity);
    }

    //public
    @Override
    public User findById(Long id) {
        if (id == null) {
            throw BaseException.nullNotAllowed(User.class.getSimpleName());
        }
        return userRepository.findById(id)
                .orElseThrow(() -> BaseException.notFound(User.class.getSimpleName(), "user", String.valueOf(id)));
    }

    //private
    private List<User> mapUserRoleToUserResponse(List<UserRole> userRoles) {
        List<User> users = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            users.add(userRole.getUser());
        }
        return users;
    }


    private User findUserById(Long id) {
        return userRepository.findUserById(id)
                .orElseThrow(
                        () -> BaseException.notFound(User.class.getSimpleName(), "user", String.valueOf(id))
                );
    }


}

package com.example.libraryproject.service.user;



import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.user.UserMapper;
import com.example.libraryproject.model.dao.*;
import com.example.libraryproject.model.dto.request.create.AdminRequestCreate;
import com.example.libraryproject.model.dto.request.update.UserRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.UserResponseAdmin;
import com.example.libraryproject.model.enums.user.RoleName;
import com.example.libraryproject.rabbitmq.EmailProducer;
import com.example.libraryproject.repository.user.RoleRepository;
import com.example.libraryproject.repository.user.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.example.libraryproject.model.enums.response.ErrorResponseMessages.EMAIL_ALREADY_REGISTERED;
import static com.example.libraryproject.model.enums.response.ErrorResponseMessages.INVALID_EMAIL_FORMAT;
import static com.example.libraryproject.model.enums.user.Status.DELETED;
import static com.example.libraryproject.model.enums.user.Status.INACTIVE;
import static com.example.libraryproject.utils.CommonUtils.throwIf;
import static com.example.libraryproject.utils.PasswordGeneratorUtils.generatePassword;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final UserMapper userMapper;
    final EmailProducer emailProducer;
//    final BCryptPasswordEncoder passwordEncoder;


    public void addUser(AdminRequestCreate adminRequestCreate,RoleName roleName)  {
        throwIf(() -> !isValidEmailAddress(adminRequestCreate.getEmail()), BaseException.of(INVALID_EMAIL_FORMAT));
        throwIf(
                () -> userRepository.findUserByEmail(adminRequestCreate.getEmail()).isPresent(),
                BaseException.of(EMAIL_ALREADY_REGISTERED)
        );
        User user = userMapper.toEntity(adminRequestCreate);
        String password = generatePassword();
        emailProducer.sendEmailMessage(user.getEmail(),password);
        user.setPassword(password);// todo: security tetbiq ederken passwordu encode edib bazaya yaz
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findRole(roleName));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public void updateUser(Long id, UserRequestUpdate userRequestUpdate) {
        User userEntity = userRepository.findUserById(id)
                .orElseThrow(
                        () -> BaseException.notFound(User.class.getSimpleName(), "user", String.valueOf(id))
                );
        userMapper.updateUserFromDto(userRequestUpdate, userEntity);
        userRepository.save(userEntity);
    }

    public void deleteUser(Long id) {
        User userEntity = userRepository.findUserById(id)
                .orElseThrow(
                        () -> BaseException.notFound(User.class.getSimpleName(), "user", String.valueOf(id))
                );
        userEntity.setStatus(DELETED);
        userRepository.save(userEntity);
    }

    public UserResponseAdmin getUserById(Long id) {
        User userEntity = userRepository.findUserById(id)
                .orElseThrow(
                        () -> BaseException.notFound(User.class.getSimpleName(), "user", String.valueOf(id))
                );
        return userMapper.toDto(userEntity);
    }

    public List<UserResponseAdmin> getAllUsers() {
        return userMapper.toDto(userRepository.findAllUser());
    }

    public UserResponseAdmin getUserByEmail(String email) {
        User userEntity = userRepository.findUserByEmail(email)
                .orElseThrow(
                        () -> BaseException.notFound(User.class.getSimpleName(), "user", email)
                );
        return userMapper.toDto(userEntity);
    }

    public List<UserResponseAdmin> getUsersByRole(RoleName roleName) {
        return userMapper.toDto(mapUserRoleToUserResponseAdmin(userRepository.getUsersByRole(roleName)));
    }

    public void deactivateUser(Long id) {
        User userEntity = userRepository.findUserById(id)
                .orElseThrow(
                        () -> BaseException.notFound(User.class.getSimpleName(), "user", String.valueOf(id))
                );
        userEntity.setStatus(INACTIVE);
        userRepository.save(userEntity);
    }

    //private

    private List<User> mapUserRoleToUserResponseAdmin(List<UserRole> userRoles) {
        List<User> users = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            users.add(userRole.getUser());
        }
        return users;
    }

    private  boolean isValidEmailAddress(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return email != null && pat.matcher(email).matches();
    }

    //public
    public User findById(Long id) {
        if (id == null) {
            throw BaseException.nullNotAllowed(User.class.getSimpleName());
        }
        return userRepository.findById(id)
                .orElseThrow(() -> BaseException.notFound(User.class.getSimpleName(), "user", String.valueOf(id)));
    }


}

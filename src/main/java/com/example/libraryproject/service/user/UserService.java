package com.example.libraryproject.service.user;


import com.example.libraryproject.constant.PasswordGenerator;
import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.user.UserMapper;
import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dao.Role;
import com.example.libraryproject.model.dao.User;
import com.example.libraryproject.model.dao.UserRole;
import com.example.libraryproject.model.dto.request.create.AdminRequestCreate;
import com.example.libraryproject.model.dto.request.create.UserRequestCreate;
import com.example.libraryproject.model.dto.request.update.UserRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.UserResponseAdmin;
import com.example.libraryproject.model.enums.user.RoleName;
import com.example.libraryproject.model.enums.user.Status;
import com.example.libraryproject.repository.user.RoleRepository;
import com.example.libraryproject.repository.user.UserRepository;
import com.example.libraryproject.repository.user.UserRoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.libraryproject.model.enums.response.ErrorResponseMessages.EMAIL_ALREADY_REGISTERED;
import static com.example.libraryproject.model.enums.user.RoleName.ADMIN;
import static com.example.libraryproject.model.enums.user.Status.DELETED;
import static com.example.libraryproject.model.enums.user.Status.INACTIVE;
import static com.example.libraryproject.utils.CommonUtils.throwIf;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    final UserRepository userRepository;
    final UserRoleRepository userRoleRepository;
    final RoleRepository roleRepository;
    final UserMapper userMapper;
    final MailService mailService;
    final RabbitTemplate rabbitTemplate;

//    final BCryptPasswordEncoder passwordEncoder;
    public void addUser(AdminRequestCreate adminRequestCreate) {
        //1: email yoxlama
        throwIf(userRepository.findUserByEmail(adminRequestCreate.getEmail())::isEmpty, BaseException.of(EMAIL_ALREADY_REGISTERED));
        //2: admin ucun default passsword generate etmek
        User user = userMapper.toEntity(adminRequestCreate);
        String password = PasswordGenerator.generatePassword();
        user.setPassword(password);
        mailService.sendMail(user.getEmail(), password);
//        user.setPassword(passwordEncoder.encode(password));
        //3: passwordun gmaile gonderilmesi rabbitmq ve prosesin tamamlanmasi
        //.....
//        emailProducer.sendEmailMessage(user.getEmail(), password);
        //4: role teyin edilmesi
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findRole(ADMIN));
        user.setRoles(roles);
        //5: bazaya insert
        System.out.println(password);
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


}

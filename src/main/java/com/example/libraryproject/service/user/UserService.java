package com.example.libraryproject.service.user;



import com.example.libraryproject.constant.PasswordGenerator;
import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.user.UserMapper;
import com.example.libraryproject.model.dao.User;
import com.example.libraryproject.model.dto.request.create.AdminRequestCreate;
import com.example.libraryproject.model.dto.request.create.UserRequestCreate;
import com.example.libraryproject.repository.user.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

import static com.example.libraryproject.model.enums.response.ErrorResponseMessages.EMAIL_ALREADY_REGISTERED;
import static com.example.libraryproject.utils.CommonUtils.throwIf;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    final UserRepository userRepository;
    final UserMapper userMapper;
    final  RabbitTemplate rabbitTemplate;

    public void addUser(AdminRequestCreate adminRequestCreate) {
        //1: email yoxlama
        throwIf( userRepository.findByEmail(adminRequestCreate.getEmail())::isPresent ,BaseException.of( EMAIL_ALREADY_REGISTERED));
        //2: admin ucun default passsword generate etmek
        User user = userMapper.toEntity(adminRequestCreate);
        String password = PasswordGenerator.generatePassword();
        user.setPassword(password);
        //3: passwordun gmaile gonderilmesi

        System.out.println(password);
        userRepository.save(user);
    }

}

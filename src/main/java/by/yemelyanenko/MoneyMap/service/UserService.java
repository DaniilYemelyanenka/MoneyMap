package by.yemelyanenko.MoneyMap.service;

import by.yemelyanenko.MoneyMap.DTO.UserDTO;
import by.yemelyanenko.MoneyMap.exception.UserAlreadyExistsException;
import by.yemelyanenko.MoneyMap.mapper.UserMapper;
import by.yemelyanenko.MoneyMap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public void registerUser(UserDTO userDTO){
        validateUserByUsername(userDTO.getUsername());
        userRepository.save(userMapper.toEntity(userDTO));
    }

    //todo Maybe make class for validations
    //todo make validation when username is null
    private void validateUserByUsername(String username){
        if(userRepository.existsByUsername(username)){
            throw new UserAlreadyExistsException("Пользователь с именем: " + username + " уже существует.");
        };
    }
}

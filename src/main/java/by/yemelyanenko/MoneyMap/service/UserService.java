package by.yemelyanenko.MoneyMap.service;

import by.yemelyanenko.MoneyMap.DTO.UserDTO;
import by.yemelyanenko.MoneyMap.entity.User;
import by.yemelyanenko.MoneyMap.exception.UserAlreadyExistsException;
import by.yemelyanenko.MoneyMap.mapper.UserMapper;
import by.yemelyanenko.MoneyMap.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private static final String USER_ALREADY_EXISTS_MSG = "Пользователь с именем: %s  уже существует.";

    public UserDTO registerUser(UserDTO userDTO){
        validateUserByUsername(userDTO.getUsername());
        User user = userRepository.save(userMapper.toEntity(userDTO));
        return userMapper.toDto(user);
    }

    private void validateUserByUsername(String username){
        if(userRepository.existsByUsername(username)){
            throw new UserAlreadyExistsException(String.format(USER_ALREADY_EXISTS_MSG,username));
        };
    }
}

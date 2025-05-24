package by.yemelyanenko.MoneyMap.service;

import by.yemelyanenko.MoneyMap.DTO.UserDTO;
import by.yemelyanenko.MoneyMap.entity.User;
import by.yemelyanenko.MoneyMap.exception.UserAlreadyExistsException;
import by.yemelyanenko.MoneyMap.exception.UserNotFoundException;
import by.yemelyanenko.MoneyMap.mapper.UserMapper;
import by.yemelyanenko.MoneyMap.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private static final String USER_ALREADY_EXISTS_MSG = "Пользователь с именем: %s  уже существует.";

    private static final String USER_NOT_FOUND_MESSAGE = "Пользователь с именем: %S не найден";

    public UserDTO loginUser(String username, String password){
        return userMapper.toDto(userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE,username))));
    }

    public UserDTO registerUser(UserDTO userDTO){
        validateUserByUsername(userDTO.getUsername());
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        User user = userRepository.save(userMapper.toEntity(userDTO));
        return userMapper.toDto(user);
    }

    private void validateUserByUsername(String username){
        if(userRepository.existsByUsername(username)){
            throw new UserAlreadyExistsException(String.format(USER_ALREADY_EXISTS_MSG,username));
        };
    }
}

package by.yemelyanenko.MoneyMap.service;

import by.yemelyanenko.MoneyMap.DTO.UserDTO;
import by.yemelyanenko.MoneyMap.entity.BasicCategories;
import by.yemelyanenko.MoneyMap.entity.Category;
import by.yemelyanenko.MoneyMap.entity.User;
import by.yemelyanenko.MoneyMap.exception.UserAlreadyExistsException;
import by.yemelyanenko.MoneyMap.exception.UserNotFoundException;
import by.yemelyanenko.MoneyMap.mapper.UserMapper;
import by.yemelyanenko.MoneyMap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserMapper userMapper;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    //todo make class for this constants
    private static final String USER_ALREADY_EXISTS_MSG = "Пользователь с именем: %s  уже существует.";
    private static final String USER_NOT_FOUND_MESSAGE = "Пользователь с именем: %S не найден";
    private static final String EMAIL_ALREADY_EXISTS_MSG = "Пользователь с email: %s  уже существует.";

    public UserDTO loginUser(String username, String password){
        return userMapper.toDto(userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE,username))));
    }


    // todo add throwing exception: user with this email is already exists
    public UserDTO registerUser(UserDTO userDTO){

        validateUserByExisting(userDTO);


        userDTO.setPassword(encoder.encode(userDTO.getPassword()));

        User user = userRepository.save(userMapper.toEntity(userDTO));

        List<Category> categoryList = categoryService.addBasicCategories(user);
        user.setCategories(categoryList);


        return userMapper.toDto(user);
    }


    //todo maybe make own class for this validating
    private void validateUserByExisting(UserDTO userDTO){
        validateUserByUsername(userDTO.getUsername());
        validateUserByEmail(userDTO.getEmail());
    }

    private void validateUserByUsername(String username){
        if(userRepository.existsByUsername(username)){
            throw new UserAlreadyExistsException(String.format(USER_ALREADY_EXISTS_MSG,username));
        };
    }

    private void validateUserByEmail(String email){
        if(userRepository.existsByEmail(email)){
            throw new UserAlreadyExistsException(String.format(EMAIL_ALREADY_EXISTS_MSG,email));
        };
    }
}

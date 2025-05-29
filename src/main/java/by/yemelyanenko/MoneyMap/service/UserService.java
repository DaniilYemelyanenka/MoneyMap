package by.yemelyanenko.MoneyMap.service;

import by.yemelyanenko.MoneyMap.DTO.UserDTO;
import by.yemelyanenko.MoneyMap.constants.MessageConstants;
import by.yemelyanenko.MoneyMap.entity.Category;
import by.yemelyanenko.MoneyMap.entity.User;
import by.yemelyanenko.MoneyMap.exception.AuthenticationException;
import by.yemelyanenko.MoneyMap.exception.UserAlreadyExistsException;
import by.yemelyanenko.MoneyMap.exception.UserNotFoundException;
import by.yemelyanenko.MoneyMap.mapper.CategoryMapper;
import by.yemelyanenko.MoneyMap.mapper.UserMapper;
import by.yemelyanenko.MoneyMap.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final CategoryService categoryService;

    private final UserMapper userMapper;

    private final CategoryMapper categoryMapper;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public String loginUser(String username,String password){
        userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                String.format(MessageConstants.USER_NOT_FOUND_MSG,username)
                        ));

        return authenticate(username,password);
    }


    public String registerUser(UserDTO userDTO){

        validateUserByExisting(userDTO);

        String password = userDTO.getPassword();
        String username = userDTO.getUsername();

        userDTO.setPassword(encoder.encode(userDTO.getPassword()));

        User user = userRepository.save(userMapper.toEntity(userDTO));

        List<Category> categoryList = categoryMapper.toEntities(categoryService.addBasicCategories(user));
        user.setCategories(categoryList);

        return authenticate(username,password);
    }


    //todo maybe make own class for this validating?
    private void validateUserByExisting(UserDTO userDTO){
        validateUserByUsername(userDTO.getUsername());
        validateUserByEmail(userDTO.getEmail());
    }

    private void validateUserByUsername(String username){
        if(userRepository.existsByUsername(username)){
            throw new UserAlreadyExistsException(
                    String.format(MessageConstants.USER_ALREADY_EXISTS_MSG,username)
            );
        };
    }

    private void validateUserByEmail(String email){
        if(userRepository.existsByEmail(email)){
            throw new UserAlreadyExistsException(
                    String.format(MessageConstants.EMAIL_ALREADY_EXISTS_MSG,email)
            );
        };
    }

    private String authenticate(String username,String password){

        String token = null;

        Authentication auth = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(username,password)
                );

        if(auth.isAuthenticated()){
            token = jwtService.generateToken(username);
        }else{
            throw new AuthenticationException(MessageConstants.AUTHENTICATION_ERROR_MSG);
        }

        return token;
    }
}

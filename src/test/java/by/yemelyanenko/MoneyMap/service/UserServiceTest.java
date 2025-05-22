package by.yemelyanenko.MoneyMap.service;

import by.yemelyanenko.MoneyMap.DTO.UserDTO;
import by.yemelyanenko.MoneyMap.entity.User;
import by.yemelyanenko.MoneyMap.exception.UserAlreadyExistsException;
import by.yemelyanenko.MoneyMap.mapper.UserMapper;
import by.yemelyanenko.MoneyMap.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;


    @InjectMocks
    private UserService userService;

    @Test
    public void registerUserTest_whenUserIsExist(){
        UserDTO user = new UserDTO();

        String username = "ivan1";

        user.setUsername(username);

        when(userRepository.existsByUsername(username)).thenReturn(true);

        Assertions.assertThrows(UserAlreadyExistsException.class,() -> userService.registerUser(user));
    }

    @Test
    public void registerUserTest_success(){

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("ivan1");

        User user = new User();
        user.setUsername("ivan1");


        when(userMapper.toEntity(userDTO)).thenReturn(user);

        when(userRepository.save(user)).thenReturn(user);

        when(userMapper.toDto(user)).thenReturn(userDTO);


        Assertions.assertEquals("ivan1",userService.registerUser(userDTO).getUsername());

        verify(userRepository).save(user);
    }


}

package by.yemelyanenko.MoneyMap.controller;


import by.yemelyanenko.MoneyMap.DTO.UserDTO;
import by.yemelyanenko.MoneyMap.exception.UserAlreadyExistsException;
import by.yemelyanenko.MoneyMap.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private UserDTO createValidUser(){
        UserDTO user = new UserDTO();
        user.setUsername("ivan1");
        user.setPassword("ivan11");
        user.setEmail("ivan@example.com");
        user.setFirstName("ivan");
        user.setSecondName("Petrov");
        return user;
    }

    public ResultActions postRequest(UserDTO user) throws Exception{
        return mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));
    }

    @Test
    public void registerUser_WhenInputValid() throws Exception {
        UserDTO user = createValidUser();

        when(userService.registerUser(user)).thenReturn(user);

        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.username").value("ivan1"))
                .andExpect(jsonPath("$.data.email").value("ivan@example.com"));
    }

    @Test
    public void registerUser_WhenInputIsNotValid_IncorrectUsernameInput() throws Exception {
        UserDTO user = createValidUser();

        user.setUsername("ivan1%");

        postRequest(user)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("username: может содержать только буквы латинского алфавита, цифры и нижнее подчеркивание"));
    }

    @Test
    public void registerUser_WhenUserIsAlreadyExists() throws Exception{
        UserDTO user = createValidUser();

        given(userService.registerUser(any(UserDTO.class)))
                .willThrow(new UserAlreadyExistsException("Пользователь с таким именем уже существует."));

        postRequest(user)
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Пользователь с таким именем уже существует."));
    }
}

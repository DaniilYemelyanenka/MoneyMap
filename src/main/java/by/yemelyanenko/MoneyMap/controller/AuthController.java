package by.yemelyanenko.MoneyMap.controller;


import by.yemelyanenko.MoneyMap.DTO.LoginDTO;
import by.yemelyanenko.MoneyMap.DTO.UserDTO;
import by.yemelyanenko.MoneyMap.response.ApiResponse;
import by.yemelyanenko.MoneyMap.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> registerUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO user = userService.registerUser(userDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<UserDTO>(true,user));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserDTO>> loginUser(@Valid @RequestBody LoginDTO loginDTO){
        UserDTO user = userService.loginUser(loginDTO.getUsername(), loginDTO.getPassword());
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(new ApiResponse<UserDTO>(true,user));
    }
}

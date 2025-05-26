package by.yemelyanenko.MoneyMap.controller;


import by.yemelyanenko.MoneyMap.DTO.LoginDTO;
import by.yemelyanenko.MoneyMap.DTO.UserDTO;
import by.yemelyanenko.MoneyMap.response.ApiResponse;
import by.yemelyanenko.MoneyMap.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerUser(@Valid @RequestBody UserDTO userDTO){

        String token = userService.registerUser(userDTO);

        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse(true, token));

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginUser(@Valid @RequestBody LoginDTO loginDTO) {

        String token = userService.loginUser(loginDTO.getUsername(),loginDTO.getPassword());

        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse(true, token));
    }


}


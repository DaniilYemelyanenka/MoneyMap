package by.yemelyanenko.MoneyMap.controller;


import by.yemelyanenko.MoneyMap.DTO.LoginDTO;
import by.yemelyanenko.MoneyMap.DTO.UserDTO;
import by.yemelyanenko.MoneyMap.exception.UserNotFoundException;
import by.yemelyanenko.MoneyMap.response.ApiResponse;
import by.yemelyanenko.MoneyMap.service.JwtService;
import by.yemelyanenko.MoneyMap.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO user = userService.registerUser(userDTO);

        Authentication auth = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
                );

        String token = null;

        if(auth.isAuthenticated()){
            token = jwtService.generateToken(user.getUsername());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse(true, token));
        }
        else {
            throw new UserNotFoundException(String.format("Пользователь с именем: %s не найден",user.getUsername()));
        }

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        UserDTO user = userService.loginUser(loginDTO.getUsername(), loginDTO.getPassword());

        Authentication auth = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
                );

        String token = null;
        if (auth.isAuthenticated()) {
            token = jwtService.generateToken(user.getUsername());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(true, token));
    }

}

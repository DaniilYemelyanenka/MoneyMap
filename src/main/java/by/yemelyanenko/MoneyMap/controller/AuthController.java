package by.yemelyanenko.MoneyMap.controller;


import by.yemelyanenko.MoneyMap.DTO.LoginDTO;
import by.yemelyanenko.MoneyMap.DTO.UserDTO;
import by.yemelyanenko.MoneyMap.response.CustomApiResponse;
import by.yemelyanenko.MoneyMap.response.ErrorResponse;
import by.yemelyanenko.MoneyMap.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@Tag(name="Контроллер аутентификации",description="контроллер для регистрации и логирования юзера")
public class AuthController {

    @Autowired
    private UserService userService;


    @Operation(
            summary = "Регистрация юзера",
            description = "регистрирует пользователя и в последущем авторизирует его в системем возвращая его токен")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "успешная регистрация",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CustomApiResponse.class))),
            @ApiResponse(responseCode = "400",
                    description = "ошибка валидации",content = @Content(
                            mediaType = "application/json",schema = @Schema(
                                    implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409",
                    description = "польователь с таким именем уже существует",
                    content = @Content(
                            mediaType = "application/json",schema = @Schema(
                                    implementation = ErrorResponse.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<CustomApiResponse<String>> registerUser(@Valid @RequestBody UserDTO userDTO){

        String token = userService.registerUser(userDTO);

        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new CustomApiResponse(true, token));

    }


    @Operation(
            summary = "Авторизация",
            description = "Авторизирует пользователя и возвращает его токен"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "успешная авторизация",
                    content = @Content(
                            mediaType = "application/json",schema = @Schema(
                                    implementation = CustomApiResponse.class))),
            @ApiResponse(responseCode = "400",
                    description = "ошибка валидации",
                    content = @Content(
                            mediaType = "application/json",schema = @Schema(
                                    implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404",
                    description = "польователь не найден",
                    content = @Content(
                            mediaType = "application/json",schema = @Schema(
                                    implementation = ErrorResponse.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<CustomApiResponse<String>> loginUser(@Valid @RequestBody LoginDTO loginDTO) {

        String token = userService.loginUser(loginDTO.getUsername(),loginDTO.getPassword());

        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new CustomApiResponse(true, token));
    }


}


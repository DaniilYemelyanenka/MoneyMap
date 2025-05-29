package by.yemelyanenko.MoneyMap.DTO;

import by.yemelyanenko.MoneyMap.constants.ValidationConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Schema(description = "Сущность пользователя")
public class UserDTO {

    @Schema(description = "Имя пользователя",example = "User1")
    @NotBlank(message = ValidationConstants.NOT_BLANC_MESSAGE)
    @Size(min = 5,max = 20,message = ValidationConstants.SIZE_MESSAGE)
    @Pattern(regexp = ValidationConstants.VALIDATION_USERNAME_REGEX,
            message = ValidationConstants.MATCHER_MESSAGE)
    private String username;

    @Schema(description = "пароль",example = "User@123")
    @NotBlank(message = ValidationConstants.NOT_BLANC_MESSAGE)
    @Size(min = 5,max = 20,message = ValidationConstants.SIZE_MESSAGE)
    @Pattern(regexp = ValidationConstants.VALIDATION_PASSWORD_REGEX,
            message = ValidationConstants.MATCHER_MESSAGE)
    private String password;

    @Schema(description = "электронная почта",example = "example@example.com")
    @NotBlank
    @Email
    private String email;

    @Schema(description = "Имя",example = "Иван",accessMode = Schema.AccessMode.READ_ONLY)
    private String firstName;

    @Schema(description = "Фамилия",example = "Петров",accessMode = Schema.AccessMode.READ_ONLY)
    private String secondName;

}

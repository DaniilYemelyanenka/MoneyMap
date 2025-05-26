package by.yemelyanenko.MoneyMap.DTO;

import by.yemelyanenko.MoneyMap.constants.ValidationConstants;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    @NotBlank(message = ValidationConstants.NOT_BLANC_MESSAGE)
    @Size(min = 5,max = 20,message = ValidationConstants.SIZE_MESSAGE)
    @Pattern(regexp = ValidationConstants.VALIDATION_USERNAME_REGEX,
            message = ValidationConstants.MATCHER_MESSAGE)
    private String username;

    @NotBlank(message = ValidationConstants.NOT_BLANC_MESSAGE)
    @Size(min = 5,max = 20,message = ValidationConstants.SIZE_MESSAGE)
    @Pattern(regexp = ValidationConstants.VALIDATION_PASSWORD_REGEX,
            message = ValidationConstants.MATCHER_MESSAGE)
    private String password;

    @NotBlank
    @Email
    private String email;

    private String firstName;

    private String secondName;

}

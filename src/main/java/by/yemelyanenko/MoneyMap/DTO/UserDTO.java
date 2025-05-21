package by.yemelyanenko.MoneyMap.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    //todo make constants of message strings

    @NotBlank(message = "должно быть заполнено")
    @Size(min = 5,max = 20,message = "должно быть не менее 5 и не более 20 символов ")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]{3,19}$",
            message = "может содержать только буквы латинского алфавита, цифры и нижнее подчеркивание")
    private String username;

    @NotBlank(message = "должно быть заполнено")
    @Size(min = 5,max = 20,message = "должно быть не менее 5 и не более 20 символов ")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$",
            message = "может содержать только буквы латинского алфавита, цифры и нижнее подчеркивание")
    private String password;

    @NotBlank
    @Email
    private String email;

    private String firstName;

    private String secondName;

    @Override
    public String toString() {
        return "UserDTO{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }
}

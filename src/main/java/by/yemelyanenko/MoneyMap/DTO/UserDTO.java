package by.yemelyanenko.MoneyMap.DTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {


    private String username;

    private String password;

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

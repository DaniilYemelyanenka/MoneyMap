package by.yemelyanenko.MoneyMap.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//todo make builder for api response
public class ApiResponse {

    private boolean success;

    private String data;

    public ApiResponse(boolean success,String string) {
        this.success = success;
        this.data = string;
    }
}

package by.yemelyanenko.MoneyMap.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
//todo make builder for api response
public class ApiResponse<T> {

    private boolean success;

    private T data;

}

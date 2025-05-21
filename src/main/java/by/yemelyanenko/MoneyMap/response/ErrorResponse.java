package by.yemelyanenko.MoneyMap.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Getter
public class ErrorResponse {

    private LocalDate timestamp;

    private int status;

    @Setter
    private String message;

    public ErrorResponse(LocalDate timestamp, HttpStatus status, String message) {
        this.timestamp = LocalDate.now();
        this.status = status.value();
        this.message = message;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = LocalDate.now();
    }

    public void setStatus(HttpStatus status) {
        this.status = status.value();
    }

}

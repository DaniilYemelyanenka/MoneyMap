package by.yemelyanenko.MoneyMap.exceptionHandel;

import by.yemelyanenko.MoneyMap.exception.UserAlreadyExistsException;
import by.yemelyanenko.MoneyMap.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> catchUserAlreadyExists(UserAlreadyExistsException userAlreadyExistsException){
        log.error(userAlreadyExistsException.getMessage(),userAlreadyExistsException);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErrorResponse(
                        LocalDate.now(),
                        HttpStatus.CONFLICT,
                        "Пользователь с таким именем уже существует."
                )
        );
    }

}

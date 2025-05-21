package by.yemelyanenko.MoneyMap.exceptionHandel;

import by.yemelyanenko.MoneyMap.exception.UserAlreadyExistsException;
import by.yemelyanenko.MoneyMap.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> catchUserAlreadyExists(UserAlreadyExistsException exception){
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErrorResponse(
                        LocalDate.now(),
                        HttpStatus.CONFLICT,
                        exception.getMessage()
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> catchArgumentNotValid(MethodArgumentNotValidException exception){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new ErrorResponse(
                LocalDate.now(),
                HttpStatus.BAD_REQUEST,
                exception.getBindingResult().getAllErrors().stream()
                        .map( error -> {
                            return ((FieldError) error).getField() + ": " + error.getDefaultMessage();})
                        .collect(Collectors.joining(", ")
                ))
        );
    }

}

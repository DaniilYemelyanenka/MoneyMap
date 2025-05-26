package by.yemelyanenko.MoneyMap.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;


public record ErrorResponse(LocalDate timestamp, HttpStatus status, String message) { }

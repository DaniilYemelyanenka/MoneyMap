package by.yemelyanenko.MoneyMap.response;

public record ApiResponse<T>(boolean success,T data){}


package by.yemelyanenko.MoneyMap.response;

public record CustomApiResponse<T>(boolean success, T data){}


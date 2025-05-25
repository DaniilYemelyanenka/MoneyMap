package by.yemelyanenko.MoneyMap.repository.specification;

import by.yemelyanenko.MoneyMap.enums.TransactionType;

import java.time.LocalDate;

public record TransactionFilter(LocalDate from, LocalDate to, String category, TransactionType type) {}

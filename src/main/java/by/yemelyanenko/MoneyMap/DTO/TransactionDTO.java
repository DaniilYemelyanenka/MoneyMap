package by.yemelyanenko.MoneyMap.DTO;

import by.yemelyanenko.MoneyMap.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private double amount;

    private TransactionType transactionType;

    private String category;

    private LocalDate date;

    private String description;
}

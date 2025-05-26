package by.yemelyanenko.MoneyMap.DTO;

import by.yemelyanenko.MoneyMap.enums.TransactionType;
import by.yemelyanenko.MoneyMap.constants.ValidationConstants;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TransactionDTO {


    @NotNull(message = ValidationConstants.NOT_BLANC_MESSAGE)
    private double amount;

    private TransactionType transactionType;

    private Long categoryId;

    private LocalDate date;

    private String description;


}

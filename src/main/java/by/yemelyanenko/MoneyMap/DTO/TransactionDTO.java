package by.yemelyanenko.MoneyMap.DTO;

import by.yemelyanenko.MoneyMap.enums.TransactionType;
import by.yemelyanenko.MoneyMap.enums.ValidationConstants;
import jakarta.validation.constraints.NotNull;
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


    @NotNull(message = ValidationConstants.NOT_BLANC_MESSAGE)
    private double amount;

    private TransactionType transactionType;

    private Long categoryId;

    private LocalDate date;

    private String description;


}

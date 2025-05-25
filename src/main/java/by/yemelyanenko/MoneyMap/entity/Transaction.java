package by.yemelyanenko.MoneyMap.entity;

import by.yemelyanenko.MoneyMap.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private TransactionType transactionType;

    private String category;

    private LocalDate date;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Transaction(double amount, String category, TransactionType transactionType, LocalDate date, String description, User user) {
        this.amount = amount;
        this.category = category;
        this.transactionType = transactionType;
        this.date = date;
        this.description = description;
        this.user = user;
    }

}

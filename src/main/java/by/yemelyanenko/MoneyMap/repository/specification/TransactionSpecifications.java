package by.yemelyanenko.MoneyMap.repository.specification;

import by.yemelyanenko.MoneyMap.entity.Transaction;
import by.yemelyanenko.MoneyMap.entity.User;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TransactionSpecifications {

    private static Specification<Transaction> belongToUser(User user){
        return (root,query,cb) -> cb.equal(root.get("user"), user);
    }

    private static Specification<Transaction> hasBetweenDate(LocalDate from,LocalDate to) {
        if(from != null && to != null){
            return (root,query,cb) -> cb.between(root.get("date"),from,to);
        }
        else if(from != null){
            return (root,query,cb) -> cb.greaterThan(root.get("date"),from);
        }
        else if(to != null){
            return (root,query,cb) -> cb.lessThan(root.get("date"),to);
        }

        return null;
    }

    //todo create specification by type


}

package by.yemelyanenko.MoneyMap.repository.specification;

import by.yemelyanenko.MoneyMap.DTO.CategoryDTO;
import by.yemelyanenko.MoneyMap.entity.Category;
import by.yemelyanenko.MoneyMap.entity.Transaction;
import by.yemelyanenko.MoneyMap.entity.User;

import by.yemelyanenko.MoneyMap.enums.TransactionType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TransactionSpecifications {

    public static Specification<Transaction> belongToUser(User user){
        return (root,query,cb) -> cb.equal(root.get("user"), user);
    }

    public static Specification<Transaction> hasBetweenDate(LocalDate from,LocalDate to) {
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

    public static Specification<Transaction> hasType(TransactionType type){

        if(type !=null){
            return (root,query,cb) -> cb.equal(root.get("transactionType"),type);
        }

        return null;
    }

    public static Specification<Transaction> hasCategory(Long id){

        if(id != null && id > 0){
            return (root,query,cb) -> cb.equal(root.get("category").get("id"),id);
        }
        return null;
    }


}

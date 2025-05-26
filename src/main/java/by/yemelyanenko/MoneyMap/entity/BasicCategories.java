package by.yemelyanenko.MoneyMap.entity;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public final class BasicCategories {

    //todo make this class separate table in DB

    public static List<Category> create(User user){
        return Arrays.asList(
                new Category("Salary",user),
                new Category("Eat",user),
                new Category("Other", user)
        );
    }
}

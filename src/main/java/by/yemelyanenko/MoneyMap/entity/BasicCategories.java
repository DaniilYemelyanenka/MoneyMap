package by.yemelyanenko.MoneyMap.entity;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public final class BasicCategories {

    public static List<Category> create(User user){
        return Arrays.asList(
                new Category("Salary",user),
                new Category("Eat",user),
                new Category("Other", user)
        );
    }
}

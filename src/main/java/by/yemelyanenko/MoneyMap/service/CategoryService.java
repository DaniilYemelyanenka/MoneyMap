package by.yemelyanenko.MoneyMap.service;


import by.yemelyanenko.MoneyMap.entity.BasicCategories;
import by.yemelyanenko.MoneyMap.entity.Category;
import by.yemelyanenko.MoneyMap.entity.User;
import by.yemelyanenko.MoneyMap.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoriesRepository;

    //todo refactor this to own table for basic categories
    public List<Category> addBasicCategories(User user){
        List<Category> listOfBasicCategory = BasicCategories.create(user);
        return categoriesRepository.saveAll(listOfBasicCategory);
    }
}

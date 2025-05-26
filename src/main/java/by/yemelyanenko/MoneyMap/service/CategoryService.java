package by.yemelyanenko.MoneyMap.service;


import by.yemelyanenko.MoneyMap.DTO.CategoryDTO;
import by.yemelyanenko.MoneyMap.entity.BasicCategories;
import by.yemelyanenko.MoneyMap.entity.Category;
import by.yemelyanenko.MoneyMap.entity.User;
import by.yemelyanenko.MoneyMap.mapper.CategoryMapper;
import by.yemelyanenko.MoneyMap.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoriesRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public List<CategoryDTO> addBasicCategories(User user){
        List<Category> listOfBasicCategory = BasicCategories.create(user);
        return categoryMapper.toDtos(categoriesRepository.saveAll(listOfBasicCategory));
    }

    public List<CategoryDTO> getAllCategories(){
       return categoryMapper.toDtos(categoriesRepository.findAll());
    }
}

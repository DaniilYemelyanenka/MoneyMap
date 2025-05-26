package by.yemelyanenko.MoneyMap.mapper.resolver;

import by.yemelyanenko.MoneyMap.entity.Category;
import by.yemelyanenko.MoneyMap.exception.NoSuchCategory;
import by.yemelyanenko.MoneyMap.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryResolver {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category fromId(Long id){
        return categoryRepository.findById(id).orElseThrow(() -> new NoSuchCategory("Такой категории не существует"));
    }

}

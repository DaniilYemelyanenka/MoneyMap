package by.yemelyanenko.MoneyMap.mapper;


import by.yemelyanenko.MoneyMap.DTO.CategoryDTO;
import by.yemelyanenko.MoneyMap.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = BaseMapper.class)
public interface CategoryMapper {



    CategoryDTO toDto (Category category);


    Category toEntity(CategoryDTO categoryDTO);

    List<CategoryDTO> toDtos(Iterable<Category> list);

    List<Category> toEntities(Iterable<CategoryDTO> list);

    Category merge(@MappingTarget Category entity, CategoryDTO dto);
}

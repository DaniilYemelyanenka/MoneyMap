package by.yemelyanenko.MoneyMap.controller;

import by.yemelyanenko.MoneyMap.DTO.CategoryDTO;
import by.yemelyanenko.MoneyMap.response.ApiResponse;
import by.yemelyanenko.MoneyMap.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getCategories(){

        List<CategoryDTO> categoryDTOList = categoryService.getAllCategories();

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(new ApiResponse<>(true,categoryDTOList));
    }
}

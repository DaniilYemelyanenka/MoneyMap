package by.yemelyanenko.MoneyMap.controller;

import by.yemelyanenko.MoneyMap.DTO.CategoryDTO;
import by.yemelyanenko.MoneyMap.response.CustomApiResponse;
import by.yemelyanenko.MoneyMap.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name="Контроллер категорий",description="контроллер для работы с категориями(получение, добавление)")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(
            summary = "получение всехз категорий",
            description = "возвращает все категории авторизированного пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302",
                    description = "категории найдены",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = CustomApiResponse.class))),
    })
    @GetMapping("/categories")
    public ResponseEntity<CustomApiResponse<List<CategoryDTO>>> getCategories(){

        List<CategoryDTO> categoryDTOList = categoryService.getAllCategories();

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(new CustomApiResponse<>(true,categoryDTOList));
    }
}

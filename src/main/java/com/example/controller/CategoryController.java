package com.example.controller;

import com.example.dto.CategoryDto;
import com.example.dto.MainCategoryDto;
import com.example.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/category/")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public MainCategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteCategory(@PathVariable long id) {
        categoryService.deleteCategory(id);
    }

    @GetMapping("{id}")
    public MainCategoryDto getCategoryById(@PathVariable long id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("categories")
    public List<MainCategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

}

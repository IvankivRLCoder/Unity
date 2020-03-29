package com.example.service;

import com.example.dto.CategoryDto;
import com.example.dto.MainCategoryDto;

import java.util.List;

public interface CategoryService {

    MainCategoryDto createCategory(CategoryDto categoryDto);

    MainCategoryDto getCategoryById(int id);

    List<MainCategoryDto> getAllCategories();

    void deleteCategory(int id);

}

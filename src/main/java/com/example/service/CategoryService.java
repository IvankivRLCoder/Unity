package com.example.service;

import com.example.dto.category.CategoryDto;
import com.example.dto.category.MainCategoryDto;
import com.example.dto.user.ApiKeyDto;

import java.util.List;

public interface CategoryService {

    MainCategoryDto createCategory(CategoryDto categoryDto);

    MainCategoryDto getCategoryById(int id, ApiKeyDto apiKeyDto);

    List<MainCategoryDto> getAllCategories(ApiKeyDto apiKeyDto);

    void deleteCategory(int id, ApiKeyDto apiKeyDto);

}

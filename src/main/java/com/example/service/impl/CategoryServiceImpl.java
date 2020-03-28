package com.example.service.impl;

import com.example.dao.CategoryDao;
import com.example.dto.CategoryDto;
import com.example.dto.MainCategoryDto;
import com.example.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    @Override
    public MainCategoryDto createCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public MainCategoryDto getCategoryById(long id) {
        return null;
    }

    @Override
    public List<MainCategoryDto> getAllCategories() {
        return null;
    }

    @Override
    public void deleteCategory(long id) {

    }

}

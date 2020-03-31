package com.example.service.impl;

import com.example.dao.CategoryDao;
import com.example.dto.category.CategoryDto;
import com.example.dto.category.MainCategoryDto;
import com.example.model.Category;
import com.example.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    private final ModelMapper modelMapper;

    @Override
    public MainCategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        return modelMapper.map(categoryDao.save(category), MainCategoryDto.class);
    }

    @Override
    public MainCategoryDto getCategoryById(int id) {
        return modelMapper.map(getById(id), MainCategoryDto.class);
    }

    @Override
    public List<MainCategoryDto> getAllCategories() {
        return categoryDao.getAll()
                .stream()
                .map(category -> modelMapper.map(category, MainCategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(int id) {
        categoryDao.delete(getById(id));
    }

    private Category getById(int id) {
        Category category = categoryDao.getById(id);
        if (category == null) {
            throw new EntityNotFoundException("Category is not found with id = " + id);
        }
        return category;
    }

}

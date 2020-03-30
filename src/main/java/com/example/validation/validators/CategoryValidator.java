package com.example.validation.validators;

import com.example.dao.CategoryDao;
import com.example.dto.category.MainCategoryDto;
import com.example.model.Category;
import com.example.validation.CategoryType;
import com.example.validation.PriorityType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@RequiredArgsConstructor
public class CategoryValidator implements ConstraintValidator<CategoryType, MainCategoryDto> {

    private final CategoryDao categoryDao;

    private final ModelMapper mapper;

    @Override
    public boolean isValid(MainCategoryDto categoryDto, ConstraintValidatorContext constraintValidatorContext) {
        if (categoryDto != null) {
            List<Category> allCategory = categoryDao.getAll();
            return allCategory.contains(mapper.map(categoryDto, Category.class));
        }
        return true;
    }

}

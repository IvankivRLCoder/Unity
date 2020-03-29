package com.example.dao;

import com.example.model.Category;

public interface CategoryDao extends MainDao<Category> {

    Category getById(long id);

    void delete(Category category);

}

package com.learn.e_commerce.service;

import com.learn.e_commerce.model.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getAllCategories();

    public void createCategory(Category category);

    public String deleteCategory(long categoryId);
}

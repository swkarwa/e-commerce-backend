package com.learn.e_commerce.service;

import com.learn.e_commerce.model.Category;

import java.util.List;

public interface CategoryService {

    public List<com.learn.e_commerce.model.Category> getAllCategories();

    public void createCategory(com.learn.e_commerce.model.Category category);

    public String deleteCategory(long categoryId);

    public Category updateCategory(long categoryId , Category category);
}

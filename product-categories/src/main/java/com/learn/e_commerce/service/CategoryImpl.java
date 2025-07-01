package com.learn.e_commerce.service;

import com.learn.e_commerce.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * if service is not provided , consider defining a bean for same
 * */
@Service
public class CategoryImpl implements CategoryService {

    List<Category> categories = new ArrayList<>();
    private long nextId = 1L;

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(long categoryId) {

        Category category = categories.stream()
                .filter(c -> c.getCategoryId() == categoryId)
                .findAny()
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found")
                );

        categories.remove(category);
        return "Category deleted successfully";
    }
}

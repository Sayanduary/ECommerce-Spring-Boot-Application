package com.ecommerce.services;

import com.ecommerce.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private List<Category> categoryList = new ArrayList<>();
    private Long nextId=1L;
    @Override
    public List<Category> getAllCategories() {
        return categoryList;
    }


    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categoryList.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        boolean removed = categoryList.removeIf(c -> c.getCategoryId() == categoryId);
        if (removed) {
            return "Category deleted successfully";
        } else {
            return "Category not found";
        }
    }



}

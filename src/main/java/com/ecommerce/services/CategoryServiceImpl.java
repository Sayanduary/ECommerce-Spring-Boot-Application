package com.ecommerce.services;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Category;
import com.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    //    private List<Category> categoryList = new ArrayList<>();
    private final Long nextId = 1L;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }


    @Override
    public String deleteCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category","CategoryId",categoryId);
        }
        categoryRepository.deleteById(categoryId);
        return "Category deleted successfully";
    }


    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",categoryId));

        // update only the fields you want
        existingCategory.setCategoryName(category.getCategoryName());

        return categoryRepository.save(existingCategory);
    }
}

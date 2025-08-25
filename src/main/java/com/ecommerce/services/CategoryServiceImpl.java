package com.ecommerce.services;

import com.ecommerce.exception.APIException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Category;
import com.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    //    private List<Category> categoryList = new ArrayList<>();
    private final Long nextId = 1L;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategories() {

        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()) {
            throw new APIException("Category not found");
        }

        return categoryRepository.findAll();
    }


    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (savedCategory != null) throw new APIException("Category " + category.getCategoryName() + " already exist");
        categoryRepository.save(category);

    }


    @Override
    public String deleteCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category", "CategoryId", categoryId);
        }
        categoryRepository.deleteById(categoryId);
        return "Category deleted successfully";
    }


    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));

        // update only the fields you want
        existingCategory.setCategoryName(category.getCategoryName());

        return categoryRepository.save(existingCategory);
    }
}

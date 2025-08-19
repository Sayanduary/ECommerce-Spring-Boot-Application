package com.ecommerce.controller;

import com.ecommerce.model.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    private final List<Category> categoryList = new ArrayList<>();

    @GetMapping("/api/public/categories")
    public List<Category> getAllCategories() {
        return categoryList;
    }

    @PostMapping("/api/public/categories")
    public String createCategory(@RequestBody Category category) {
        categoryList.add(category);
        return "Category added successfully";
    }
}

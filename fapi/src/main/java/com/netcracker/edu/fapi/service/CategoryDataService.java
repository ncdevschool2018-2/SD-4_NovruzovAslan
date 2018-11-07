package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.CategoryViewModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryDataService {
    List<CategoryViewModel> getAll();
    CategoryViewModel getCategoryById(Long id);
    CategoryViewModel saveCategory(CategoryViewModel category);
    void deleteCategory(Long id);
}

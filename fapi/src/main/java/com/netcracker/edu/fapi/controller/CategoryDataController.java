package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.CategoryViewModel;
import com.netcracker.edu.fapi.service.CategoryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryDataController {

    @Autowired
    private CategoryDataService categoryDataService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryViewModel>> getAllCategories() {
        return ResponseEntity.ok(categoryDataService.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CategoryViewModel> getCategoryById(@PathVariable(name="id") String id) {
        return ResponseEntity.ok(categoryDataService.getCategoryById(Long.valueOf(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<CategoryViewModel> saveCategory(@RequestBody CategoryViewModel category /*todo server validation*/) {
        if (category != null) {
            return ResponseEntity.ok(categoryDataService.saveCategory(category));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('admin')")
    public void deleteCategory(@PathVariable String id) {
        categoryDataService.deleteCategory(Long.valueOf(id));
    }

}


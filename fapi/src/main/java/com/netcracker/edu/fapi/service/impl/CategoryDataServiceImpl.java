package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.CategoryViewModel;
import com.netcracker.edu.fapi.service.CategoryDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CategoryDataServiceImpl implements CategoryDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<CategoryViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        CategoryViewModel[] categoryViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/categories", CategoryViewModel[].class);
        return categoryViewModelResponse == null ? Collections.emptyList() : Arrays.asList(categoryViewModelResponse);
    }

    @Override
    public CategoryViewModel getCategoryById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        CategoryViewModel categoryViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/categories/" + String.valueOf(id), CategoryViewModel.class);
        return categoryViewModelResponse;
    }

    @Override
    public CategoryViewModel saveCategory(CategoryViewModel category) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/categories", category, CategoryViewModel.class).getBody();
    }

    @Override
    public void deleteCategory(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/categories/" + id);
    }
}

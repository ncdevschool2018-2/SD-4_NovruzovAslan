package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.ProductViewModel;
import com.netcracker.edu.fapi.service.ProductDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ProductDataServiceImpl implements ProductDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<ProductViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        ProductViewModel[] productViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/products", ProductViewModel[].class);
        return productViewModelResponse == null ? Collections.emptyList() : Arrays.asList(productViewModelResponse);
    }

    @Override
    public ProductViewModel getProductById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ProductViewModel productViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/products/" + String.valueOf(id), ProductViewModel.class);
        return productViewModelResponse;
    }

    @Override
    public List<ProductViewModel> getProductsByCategoryId(Long category_id) {
        RestTemplate restTemplate = new RestTemplate();
        ProductViewModel[] productViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/products/?category_id={category_id}", ProductViewModel[].class, String.valueOf(category_id));
        return productViewModelResponse == null ? Collections.emptyList() : Arrays.asList(productViewModelResponse);
    }

    @Override
    public ProductViewModel saveProduct(ProductViewModel product) {
        String category_id = String.valueOf(product.getCategory().getId());
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/products/" + category_id, product, ProductViewModel.class).getBody();
    }

    @Override
    public void deleteProduct(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/products/" + id);
    }
}

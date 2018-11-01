package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.ProductViewModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductDataService {
    List<ProductViewModel> getAll();
    ProductViewModel getProductById(Long id);
    ProductViewModel saveProduct(ProductViewModel product);
    void deleteProduct(Long id);
}

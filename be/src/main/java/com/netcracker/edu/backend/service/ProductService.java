package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Product;

import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product user);
    Optional<Product> getProductById(Long id);
    Iterable<Product> getAllProducts();
    void deleteProduct(Long id);
}

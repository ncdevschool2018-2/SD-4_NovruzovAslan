package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product user);
    Optional<Product> getProductById(Long id);
    Iterable<Product> getAllProducts();
    Iterable<Product> getProductsByUserId(Long id);
//    Iterable<Product> getProductsByCategoryId(Long id);
    void deleteProduct(Long id);
    Page<Product> getPage(Integer page, Integer size, Long category_id);
    Page<Product> getOwnPage(Integer page, Integer size, Long manager_id);
}

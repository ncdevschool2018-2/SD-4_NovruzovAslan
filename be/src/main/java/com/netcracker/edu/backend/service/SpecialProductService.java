package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.SpecialProduct;

import java.util.Optional;

public interface SpecialProductService {
    SpecialProduct saveSpecialProduct(SpecialProduct category);
    Optional<SpecialProduct> getSpecialProductById(Long id);
    Iterable<SpecialProduct> getAllSpecialProducts();
    void deleteSpecialProduct(Long id);
}

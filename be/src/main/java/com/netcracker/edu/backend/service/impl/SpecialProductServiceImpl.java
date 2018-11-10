package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.SpecialProduct;
import com.netcracker.edu.backend.repository.SpecialProductRepository;
import com.netcracker.edu.backend.service.SpecialProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpecialProductServiceImpl implements SpecialProductService {

    private SpecialProductRepository repository;

    @Autowired
    public SpecialProductServiceImpl(SpecialProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public SpecialProduct saveSpecialProduct(SpecialProduct product) {
        return repository.save(product);
    }

    @Override
    public Optional<SpecialProduct> getSpecialProductById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<SpecialProduct> getAllSpecialProducts() {
        return repository.findAll();
    }

    @Override
    public void deleteSpecialProduct(Long id) {
        repository.deleteById(id);
    }
}

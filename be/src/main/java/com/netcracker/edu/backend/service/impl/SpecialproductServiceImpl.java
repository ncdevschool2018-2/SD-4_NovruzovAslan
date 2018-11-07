package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Specialproduct;
import com.netcracker.edu.backend.repository.SpecialproductRepository;
import com.netcracker.edu.backend.service.SpecialproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpecialproductServiceImpl implements SpecialproductService {

    private SpecialproductRepository repository;

    @Autowired
    public SpecialproductServiceImpl(SpecialproductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Specialproduct saveSpecialproduct(Specialproduct product) {
        return repository.save(product);
    }

    @Override
    public Optional<Specialproduct> getSpecialproductById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Specialproduct> getAllSpecialproducts() {
        return repository.findAll();
    }

    @Override
    public void deleteSpecialproduct(Long id) {
        repository.deleteById(id);
    }
}

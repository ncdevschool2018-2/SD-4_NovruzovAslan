package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Specialproduct;

import java.util.Optional;

public interface SpecialproductService {
    Specialproduct saveSpecialproduct(Specialproduct category);
    Optional<Specialproduct> getSpecialproductById(Long id);
    Iterable<Specialproduct> getAllSpecialproducts();
    void deleteSpecialproduct(Long id);
}

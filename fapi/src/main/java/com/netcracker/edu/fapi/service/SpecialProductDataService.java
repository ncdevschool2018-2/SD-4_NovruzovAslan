package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.SpecialProductViewModel;

import java.util.List;

public interface SpecialProductDataService {
    List<SpecialProductViewModel> getAll();
    SpecialProductViewModel getSpecialProductById(Long id);
    SpecialProductViewModel saveSpecialProduct(SpecialProductViewModel specialProduct);
    void deleteSpecialProduct(Long id);
}

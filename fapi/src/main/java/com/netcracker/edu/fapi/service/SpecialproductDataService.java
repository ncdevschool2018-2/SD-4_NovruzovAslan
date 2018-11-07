package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.SpecialproductViewModel;

import java.util.List;

public interface SpecialproductDataService {
    List<SpecialproductViewModel> getAll();
    SpecialproductViewModel getSpecialproductById(Long id);
    SpecialproductViewModel saveSpecialproduct(SpecialproductViewModel specialproduct);
    void deleteSpecialproduct(Long id);
}

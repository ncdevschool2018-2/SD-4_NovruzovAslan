package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.RateViewModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RateDataService {
    List<RateViewModel> getAll();
    RateViewModel getRateById(Long id);
    RateViewModel saveRate(RateViewModel rate);
    void deleteRate(Long id);
}

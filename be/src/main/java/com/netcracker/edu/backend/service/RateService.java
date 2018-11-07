package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Rate;

import java.util.Optional;

public interface RateService {
    Rate saveRate(Rate category);
    Optional<Rate> getRateById(Long id);
    Iterable<Rate> getAllRates();
    void deleteRate(Long id);
}

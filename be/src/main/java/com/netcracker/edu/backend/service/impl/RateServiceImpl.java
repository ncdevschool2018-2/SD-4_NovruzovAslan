package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Rate;
import com.netcracker.edu.backend.repository.RateRepository;
import com.netcracker.edu.backend.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RateServiceImpl implements RateService {

    private RateRepository repository;

    @Autowired
    public RateServiceImpl(RateRepository repository) {
        this.repository = repository;
    }

    @Override
    public Rate saveRate(Rate rate) {
        return repository.save(rate);
    }

    @Override
    public Optional<Rate> getRateById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Rate> getAllRates() {
        return repository.findAll();
    }

    @Override
    public void deleteRate(Long id) {
        repository.deleteById(id);
    }
}

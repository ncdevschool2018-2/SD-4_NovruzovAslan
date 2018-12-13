package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.SpecialSubscription;
import com.netcracker.edu.backend.repository.SpecialSubscriptionRepository;
import com.netcracker.edu.backend.service.SpecialSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpecialSubscriptionServiceImpl implements SpecialSubscriptionService {

    private SpecialSubscriptionRepository repository;

    @Autowired
    public SpecialSubscriptionServiceImpl(SpecialSubscriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public SpecialSubscription saveSpecialSubscription(SpecialSubscription specialSubscription) {
        return repository.save(specialSubscription);
    }

    @Override
    public Optional<SpecialSubscription> getSpecialSubscriptionById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<SpecialSubscription> getSpecialSubscriptionsByUserId(Long id) {
        return repository.findSubscriptionsByUserWalletId(id);
    }

    @Override
    public Iterable<SpecialSubscription> getAllSpecialSubscriptions() {
        return repository.findAll();
    }

    @Override
    public Double calculatePrice(SpecialSubscription specialSubscription) {
        // todo: convertation from one valute to another
        Double fullPrice = 0D;//specialSubscription.getSpecialProduct().getProduct().getCost();
        // todo: make discount depending from
        Double pricePerDay = fullPrice/30;
        return pricePerDay;
    }

    @Override
    public void deleteSpecialSubscription(Long id) {
        repository.deleteById(id);
    }
}

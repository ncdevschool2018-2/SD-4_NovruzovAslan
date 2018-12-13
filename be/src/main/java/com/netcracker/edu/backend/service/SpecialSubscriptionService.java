package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.SpecialSubscription;

import java.util.Optional;

public interface SpecialSubscriptionService {
    SpecialSubscription saveSpecialSubscription(SpecialSubscription category);
    Optional<SpecialSubscription> getSpecialSubscriptionById(Long id);
    Iterable<SpecialSubscription> getSpecialSubscriptionsByUserId(Long id);
    Iterable<SpecialSubscription> getAllSpecialSubscriptions();
    Double calculatePrice(SpecialSubscription subscription);
    void deleteSpecialSubscription(Long id);
}

package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Subscription;

import java.util.Optional;

public interface SubscriptionService {
    Subscription saveSubscription(Subscription category);
    Optional<Subscription> getSubscriptionById(Long id);
    Iterable<Subscription> getAllSubscriptions();
    void deleteSubscription(Long id);
}

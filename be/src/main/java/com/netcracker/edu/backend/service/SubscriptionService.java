package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Subscription;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface SubscriptionService {
    Subscription saveSubscription(Subscription category);
    Optional<Subscription> getSubscriptionById(Long id);
    Iterable<Subscription> getSubscriptionsByUserId(Long id);
    Page<Subscription> getSubscriptionsPageByUserId(Integer page, Integer size, Long userId);
    Iterable<Subscription> getAllSubscriptions();
    Double calculatePrice(Subscription subscription);
    void unsubscribe(Long prodId, Long userId);
    void deleteSubscription(Long id);
}

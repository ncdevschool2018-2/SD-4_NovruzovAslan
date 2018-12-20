package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.SpecialSubscription;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface SpecialSubscriptionService {
    SpecialSubscription saveSpecialSubscription(SpecialSubscription subscription);
    Optional<SpecialSubscription> getSpecialSubscriptionById(Long id);
    Iterable<SpecialSubscription> getSpecialSubscriptionsByUserId(Long id);
    Page<SpecialSubscription> getSpecialSubscriptionsPageByUserId(Integer page, Integer size, Long userId);
    Iterable<SpecialSubscription> getAllSpecialSubscriptions(Long userId);
    Double calculatePrice(SpecialSubscription subscription);
    void unsubscribe(Long prodId, Long userId);
    void deleteSpecialSubscription(Long id);
}

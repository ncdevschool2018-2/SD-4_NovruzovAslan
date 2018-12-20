package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.SubscriptionViewModel;

import java.util.List;

public interface SubscriptionDataService {
    List<SubscriptionViewModel> getAll(Long userId);
    SubscriptionViewModel getSubscriptionById(Long id);
    List<SubscriptionViewModel> getSubscriptionsPageByUserId(Integer page, Integer size, Long userId);
    SubscriptionViewModel getSubscriptionByUserAndProductId(Long userId, Long productId);
    Integer getTotalPages(Integer size, Long user_id);
    SubscriptionViewModel saveSubscription(SubscriptionViewModel subscription);
    void unsubscribe(String prodId, String userId);
    void deleteSubscription(Long id);
}

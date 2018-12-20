package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.SpecialSubscriptionViewModel;

import java.util.List;

public interface SpecialSubscriptionDataService {
    List<SpecialSubscriptionViewModel> getAll(Long userId);
    SpecialSubscriptionViewModel getSpecialSubscriptionById(Long id);
    List<SpecialSubscriptionViewModel> getSpecialSubscriptionsPageByUserId(Integer page, Integer size, Long userId);
    Integer getTotalPages(Integer size, Long user_id);
    SpecialSubscriptionViewModel saveSpecialSubscription(SpecialSubscriptionViewModel subscription);
    void unsubscribe(String prodId, String userId);
    void deleteSpecialSubscription(Long id);
}

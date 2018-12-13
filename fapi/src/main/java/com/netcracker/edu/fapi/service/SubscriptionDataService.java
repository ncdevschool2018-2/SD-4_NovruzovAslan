package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.SubscriptionViewModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubscriptionDataService {
    List<SubscriptionViewModel> getAll();
    SubscriptionViewModel getSubscriptionById(Long id);
    List<SubscriptionViewModel> getProductsByUserId(Long page, Long userId);
    Integer getTotalPages(Long user_id);
    SubscriptionViewModel saveSubscription(SubscriptionViewModel subscription);
    void unsubscribe(String prodId, String userId);
    void deleteSubscription(Long id);
}

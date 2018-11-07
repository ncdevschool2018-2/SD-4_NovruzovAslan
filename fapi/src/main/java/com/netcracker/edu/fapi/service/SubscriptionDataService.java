package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.SubscriptionViewModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubscriptionDataService {
    List<SubscriptionViewModel> getAll();
    SubscriptionViewModel getSubscriptionById(Long id);
    SubscriptionViewModel saveSubscription(SubscriptionViewModel subscription);
    void deleteSubscription(Long id);
}

package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.SubscriptionViewModel;
import com.netcracker.edu.fapi.service.SubscriptionDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SubscriptionDataServiceImpl implements SubscriptionDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<SubscriptionViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        SubscriptionViewModel[] subscriptionViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/subscriptions", SubscriptionViewModel[].class);
        return subscriptionViewModelResponse == null ? Collections.emptyList() : Arrays.asList(subscriptionViewModelResponse);
    }

    @Override
    public SubscriptionViewModel getSubscriptionById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        SubscriptionViewModel subscriptionViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/subscriptions/" + String.valueOf(id), SubscriptionViewModel.class);
        return subscriptionViewModelResponse;
    }

    @Override
    public SubscriptionViewModel saveSubscription(SubscriptionViewModel subscription) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/subscriptions", subscription, SubscriptionViewModel.class).getBody();
    }

    @Override
    public void deleteSubscription(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/subscriptions/" + id);
    }
}

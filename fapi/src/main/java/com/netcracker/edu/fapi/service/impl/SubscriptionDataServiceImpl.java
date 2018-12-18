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
    public List<SubscriptionViewModel> getSubscriptionsPageByUserId(Integer page, Integer size, Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = backendServerUrl + "/api/subscriptions/products?page=" + page + "&size=" + size + "&user_id=" + userId;
        SubscriptionViewModel[] productViewModelResponse = restTemplate.getForObject(url, SubscriptionViewModel[].class);
        return productViewModelResponse == null ? Collections.emptyList() : Arrays.asList(productViewModelResponse);

    }

    @Override
    public Integer getTotalPages(Integer size, Long user_id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = backendServerUrl+"/api/subscriptions/total-pages?size=" + size + "&user_id=" + user_id;
        return restTemplate.getForObject(url, Integer.class);
    }

    @Override
    public SubscriptionViewModel saveSubscription(SubscriptionViewModel subscription) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/subscriptions", subscription, SubscriptionViewModel.class).getBody();
    }

    @Override
    public void unsubscribe(String prodId, String userId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(
                backendServerUrl + "/api/subscriptions?product_id=" + prodId +
                        "&user_id=" + userId);
    }

    @Override
    public void deleteSubscription(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/subscriptions/" + id);
    }
}

package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.SpecialSubscriptionViewModel;
import com.netcracker.edu.fapi.service.SpecialSubscriptionDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SpecialSubscriptionDataServiceImpl implements SpecialSubscriptionDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<SpecialSubscriptionViewModel> getAll(Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = backendServerUrl + "/api/special-subscriptions";
        if(userId != null)
            url += "?user_id="+userId;
        SpecialSubscriptionViewModel[] SpecialSubscriptionViewModelResponse = restTemplate.getForObject(url, SpecialSubscriptionViewModel[].class);
        return SpecialSubscriptionViewModelResponse == null ? Collections.emptyList() : Arrays.asList(SpecialSubscriptionViewModelResponse);
    }

    @Override
    public SpecialSubscriptionViewModel getSpecialSubscriptionById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        SpecialSubscriptionViewModel SpecialSubscriptionViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/special-subscriptions/" + String.valueOf(id), SpecialSubscriptionViewModel.class);
        return SpecialSubscriptionViewModelResponse;
    }

    @Override
    public List<SpecialSubscriptionViewModel> getSpecialSubscriptionsPageByUserId(Integer page, Integer size, Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = backendServerUrl + "/api/special-subscriptions/products?page=" + page + "&size=" + size + "&user_id=" + userId;
        SpecialSubscriptionViewModel[] productViewModelResponse = restTemplate.getForObject(url, SpecialSubscriptionViewModel[].class);
        return productViewModelResponse == null ? Collections.emptyList() : Arrays.asList(productViewModelResponse);

    }

    @Override
    public Integer getTotalPages(Integer size, Long user_id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = backendServerUrl+"/api/special-subscriptions/total-pages?size=" + size + "&user_id=" + user_id;
        return restTemplate.getForObject(url, Integer.class);
    }

    @Override
    public SpecialSubscriptionViewModel saveSpecialSubscription(SpecialSubscriptionViewModel subscription) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/special-subscriptions", subscription, SpecialSubscriptionViewModel.class).getBody();
    }

    @Override
    public void unsubscribe(String prodId, String userId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(
                backendServerUrl + "/api/special-subscriptions?product_id=" + prodId +
                        "&user_id=" + userId);
    }

    @Override
    public void deleteSpecialSubscription(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/special-subscriptions/" + id);
    }
}

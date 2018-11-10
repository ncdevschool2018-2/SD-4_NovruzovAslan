package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.RateViewModel;
import com.netcracker.edu.fapi.service.RateDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RateDataServiceImpl implements RateDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<RateViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        RateViewModel[] rateViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/rates", RateViewModel[].class);
        return rateViewModelResponse == null ? Collections.emptyList() : Arrays.asList(rateViewModelResponse);
    }

    @Override
    public RateViewModel getRateById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        RateViewModel rateViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/rates/" + String.valueOf(id), RateViewModel.class);
        return rateViewModelResponse;
    }

    @Override
    public RateViewModel saveRate(RateViewModel rate) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/rates", rate, RateViewModel.class).getBody();
    }

    @Override
    public void deleteRate(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/rates/" + id);
    }
}

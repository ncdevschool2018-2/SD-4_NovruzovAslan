package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.SpecialproductViewModel;
import com.netcracker.edu.fapi.service.SpecialproductDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SpecialproductDataServiceImpl implements SpecialproductDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<SpecialproductViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        SpecialproductViewModel[] specialproductViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/specialproducts", SpecialproductViewModel[].class);
        return specialproductViewModelResponse == null ? Collections.emptyList() : Arrays.asList(specialproductViewModelResponse);
    }

    @Override
    public SpecialproductViewModel getSpecialproductById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        SpecialproductViewModel specialproductViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/specialproducts/" + String.valueOf(id), SpecialproductViewModel.class);
        return specialproductViewModelResponse;
    }

    @Override
    public SpecialproductViewModel saveSpecialproduct(SpecialproductViewModel specialproduct) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/specialproducts", specialproduct, SpecialproductViewModel.class).getBody();
    }

    @Override
    public void deleteSpecialproduct(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/specialproducts/" + id);
    }
}

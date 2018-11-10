package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.SpecialProductViewModel;
import com.netcracker.edu.fapi.service.SpecialProductDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SpecialProductDataServiceImpl implements SpecialProductDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<SpecialProductViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        SpecialProductViewModel[] specialProductViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/specialproducts", SpecialProductViewModel[].class);
        return specialProductViewModelResponse == null ? Collections.emptyList() : Arrays.asList(specialProductViewModelResponse);
    }

    @Override
    public SpecialProductViewModel getSpecialProductById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        SpecialProductViewModel specialProductViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/specialproducts/" + String.valueOf(id), SpecialProductViewModel.class);
        return specialProductViewModelResponse;
    }

    @Override
    public SpecialProductViewModel saveSpecialProduct(SpecialProductViewModel specialproduct) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/specialproducts", specialproduct, SpecialProductViewModel.class).getBody();
    }

    @Override
    public void deleteSpecialProduct(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/specialproducts/" + id);
    }
}

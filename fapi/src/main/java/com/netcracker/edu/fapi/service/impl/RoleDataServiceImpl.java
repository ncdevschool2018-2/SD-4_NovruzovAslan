package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.RoleViewModel;
import com.netcracker.edu.fapi.service.RoleDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RoleDataServiceImpl implements RoleDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<RoleViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        RoleViewModel[] roleViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/roles", RoleViewModel[].class);
        return roleViewModelResponse == null ? Collections.emptyList() : Arrays.asList(roleViewModelResponse);
    }

    @Override
    public RoleViewModel getRoleById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        RoleViewModel roleViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/roles/" + String.valueOf(id), RoleViewModel.class);
        return roleViewModelResponse;
    }

    @Override
    public RoleViewModel saveRole(RoleViewModel role) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/roles", role, RoleViewModel.class).getBody();
    }

    @Override
    public void deleteRole(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/roles/" + id);
    }
}

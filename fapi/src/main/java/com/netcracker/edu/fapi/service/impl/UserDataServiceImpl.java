package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UserDataServiceImpl implements UserDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<UserViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        UserViewModel[] userViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/users", UserViewModel[].class);
        return userViewModelResponse == null ? Collections.emptyList() : Arrays.asList(userViewModelResponse);
    }

    @Override
    public UserViewModel getUserById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        UserViewModel userViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/users/" + String.valueOf(id), UserViewModel.class);
        return userViewModelResponse;
    }

    @Override
    public UserViewModel saveUser(UserViewModel user) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/users", user, UserViewModel.class).getBody();
    }

    @Override
    public void deleteUser(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/users/" + id);
    }
}

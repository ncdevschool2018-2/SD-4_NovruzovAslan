package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.UserInfoViewModel;
import com.netcracker.edu.fapi.service.UserInfoDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UserInfoDataServiceImpl implements UserInfoDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<UserInfoViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        UserInfoViewModel[] userinfoViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/userinfos", UserInfoViewModel[].class);
        return userinfoViewModelResponse == null ? Collections.emptyList() : Arrays.asList(userinfoViewModelResponse);
    }

    @Override
    public UserInfoViewModel getUserInfoById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        UserInfoViewModel userInfoViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/userInfos/" + String.valueOf(id), UserInfoViewModel.class);
        return userInfoViewModelResponse;
    }

    @Override
    public UserInfoViewModel saveUserInfo(UserInfoViewModel userinfo) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/userinfos", userinfo, UserInfoViewModel.class).getBody();
    }

    @Override
    public void deleteUserInfo(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/userinfos/" + id);
    }
}

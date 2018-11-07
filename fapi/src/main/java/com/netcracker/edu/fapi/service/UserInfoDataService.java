package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.UserInfoViewModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserInfoDataService {
    List<UserInfoViewModel> getAll();
    UserInfoViewModel getUserInfoById(Long id);
    UserInfoViewModel saveUserInfo(UserInfoViewModel userInfo);
    void deleteUserInfo(Long id);
}

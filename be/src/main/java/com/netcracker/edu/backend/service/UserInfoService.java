package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.UserInfo;

import java.util.Optional;

public interface UserInfoService {

    UserInfo saveUserInfo(UserInfo user);
    Optional<UserInfo> getUserInfoById(Long id);
    Iterable<UserInfo> getAllUserInfos();
    void deleteUserInfo(Long id);
}

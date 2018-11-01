package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.UserInfo;
import com.netcracker.edu.backend.repository.UserInfoRepository;
import com.netcracker.edu.backend.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoServiceImpl implements UserInfoService {

    private UserInfoRepository repository;

    @Autowired
    public UserInfoServiceImpl(UserInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserInfo saveUserInfo(UserInfo user) {
        return repository.save(user);
    }

    @Override
    public Optional<UserInfo> getUserInfoById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<UserInfo> getAllUserInfos() {
        return repository.findAll();
    }

    @Override
    public void deleteUserInfo(Long id) {
        repository.deleteById(id);
    }
}

package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.UserViewModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserDataService {
    List<UserViewModel> getAll();
    UserViewModel getUserById(Long id);
    UserViewModel saveUser(UserViewModel user);
    void deleteUser(Long id);
}

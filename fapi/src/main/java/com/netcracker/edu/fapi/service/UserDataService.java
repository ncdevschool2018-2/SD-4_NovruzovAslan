package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.RoleViewModel;
import com.netcracker.edu.fapi.models.UserViewModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserDataService {
    List<UserViewModel> getAll(Integer page, Integer size);
    Integer getTotalPages(Integer size);
    UserViewModel getUserById(Long id);
    UserViewModel getUserByUsername(String username);
    UserViewModel saveUser(UserViewModel user);
    UserViewModel changeRole(UserViewModel user, Integer roleId);
    UserViewModel getBestUser();
    String getUsername(String token);
    void deleteUser(Long id);
}

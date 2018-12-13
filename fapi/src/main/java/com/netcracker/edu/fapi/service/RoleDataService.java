package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.RoleViewModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleDataService {
    List<RoleViewModel> getAll();
    RoleViewModel getRoleById(Long id);
    RoleViewModel saveRole(RoleViewModel role);
    void deleteRole(Long id);
}

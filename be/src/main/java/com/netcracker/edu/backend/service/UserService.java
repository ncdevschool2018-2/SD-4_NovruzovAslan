package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {

    User saveUser(User user);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByUsername(String username);
    User getBestManager();
    Page<User> getAllUsers(Integer page, Integer size);
    User changeRole(User user, Long roleId);
    void deleteUser(Long id);
}

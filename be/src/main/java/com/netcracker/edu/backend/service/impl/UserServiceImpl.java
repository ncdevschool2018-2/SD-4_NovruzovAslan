package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Product;
import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.ProductService;
import com.netcracker.edu.backend.service.RoleService;
import com.netcracker.edu.backend.service.SubscriptionService;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ProductService productService;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User saveUser(User user) {
        Boolean temp = repository.findByUsername(user.getUsername()).isPresent();
        if(user.getId()!=null || temp==false)
            return repository.save(user);
        else return null;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public User getBestManager() {
        Iterable<User> users = repository.findAllUsersByRoleId(2L);
        Iterable<Product> products;
        User bestUser = null;
        Integer counter = 0;
        Integer bestCounter = 0;
        for(User user: users) {
            products = productService.getProductsByUserId(user.getId());
            for(Product product: products) {
                counter++;
            }
            if(counter>bestCounter) {
                bestCounter = counter;
                bestUser = user;
                counter = 0;
            }
        }
        return bestUser;
    }

    @Override
    public Page<User> getAllUsers(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page-1, size, new Sort(Sort.Direction.ASC, "id"));
        return repository.findAll(pageable);
    }

    @Override
    public User changeRole(User user, Long roleId) {
        Boolean temp = repository.findByUsername(user.getUsername()).isPresent();
        if(user.getId()!=null || temp==false) {
            user.setRole(roleService.getRoleById(roleId).get());
            subscriptionService.deleteAllByUserId(user.getId());
            return repository.save(user);
        }
        else return null;
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}

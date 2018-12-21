package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.config.Constants;
import com.netcracker.edu.fapi.config.JwtTokenUtil;
import com.netcracker.edu.fapi.models.RoleViewModel;
import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service(value = "userDataService")
public class UserDataServiceImpl implements UserDetailsService, UserDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserViewModel user = getUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("Invalid username or password.");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set getAuthority(UserViewModel user) {
        Set authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return authorities;
    }

    @Override
    public List<UserViewModel> getAll(Integer page, Integer size) {
        RestTemplate restTemplate = new RestTemplate();
        UserViewModel[] userViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/users?page="+page+"&size="+size, UserViewModel[].class);
        return userViewModelResponse == null ? Collections.emptyList() : Arrays.asList(userViewModelResponse);
    }

    @Override
    public Integer getTotalPages(Integer size) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl+"/api/users/total-pages?size=" + size, Integer.class);
    }

    @Override
    public UserViewModel getUserById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        UserViewModel userViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/users/" + String.valueOf(id), UserViewModel.class);
        return userViewModelResponse;
    }

    @Override
    public UserViewModel getUserByUsername(String username) {
        RestTemplate restTemplate = new RestTemplate();
        UserViewModel userViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/users/u/" + username, UserViewModel.class);
        return userViewModelResponse;
    }

//    @Override
//    public UserViewModel saveUser(UserViewModel user) {
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.postForEntity(backendServerUrl + "/api/users", user, UserViewModel.class).getBody();
//    }

    @Override
    public UserViewModel saveUser(UserViewModel user) {
        RestTemplate restTemplate = new RestTemplate();
//        UserViewModel newUser = new UserViewModel();
//        newUser.setUsername(user.getUsername());
        String newPass = bcryptEncoder.encode(user.getPassword());
        user.setPassword(newPass);
        return restTemplate.postForEntity(backendServerUrl + "/api/users", user, UserViewModel.class).getBody();
    }

    @Override
    public UserViewModel changeRole(UserViewModel user, Integer roleId) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/users/change-role?new="+roleId, user, UserViewModel.class).getBody();
    }

    @Override
    public UserViewModel getBestUser() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/users/best", UserViewModel.class);

    }

    @Override
    public void deleteUser(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/users/" + id);
    }

    @Override
    public String getUsername(String bearerToken) {
        String login = null;
        String authToken = bearerToken.replace(Constants.TOKEN_PREFIX, "");
        try {
            login = jwtTokenUtil.getUsernameFromToken(authToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return login;
    }

}

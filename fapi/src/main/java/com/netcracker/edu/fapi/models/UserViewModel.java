package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserViewModel {

    private Long id;
    @NotBlank
    @Size(min=4, max = 40)
    private String username;
    @NotBlank
    @Size(min=6, max = 20)
    private String password;
    @NotNull
    @Valid
    @JsonManagedReference
    private UserInfoViewModel userInfo;
    @NotNull
    @Valid
    private RoleViewModel role;

    public UserViewModel() {
    }

    public UserViewModel(Long id, String username, String password, UserInfoViewModel userInfo, RoleViewModel role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userInfo = new UserInfoViewModel(userInfo);
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfoViewModel getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoViewModel userInfo) {
        this.userInfo = userInfo;
    }

    public RoleViewModel getRole() {
        return role;
    }

    public void setRole(RoleViewModel role) {
        this.role = role;
    }
}

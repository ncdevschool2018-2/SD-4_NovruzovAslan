package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoViewModel {

    private Long id;
    @JsonBackReference
    private UserViewModel user;
    private String name;
    private Date dateOfBirth;

    public UserInfoViewModel() {
    }

    public UserInfoViewModel(UserInfoViewModel user){
        this.id = user.id;
        this.name = user.name;
        this.dateOfBirth = user.dateOfBirth;
    }

    public UserInfoViewModel(Long id, String name, Date dateOfBirth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserViewModel getUser() {
        return user;
    }

    public void setUser(UserViewModel user) {
        this.user = user;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}

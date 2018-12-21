package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoViewModel {

    private Long id;
    @NotNull
    @Valid
    @JsonBackReference
    private UserViewModel user;
    @NotBlank
    @Size(max = 40)
    private String name;
    @Past
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

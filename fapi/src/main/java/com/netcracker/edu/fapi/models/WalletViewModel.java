package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WalletViewModel {

    private Long id;
//    @JsonIgnore
    private UserViewModel user;
    private String name;
    private String description;
    private Double value;
    private Character valute;

    public WalletViewModel() {
    }

    public WalletViewModel(Long id, UserViewModel user, String name, String description, Double value, Character valute) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.description = description;
        this.value = value;
        this.valute = valute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserViewModel getUser() {
        return user;
    }

    public void setUser(UserViewModel user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Character getValute() {
        return valute;
    }

    public void setValute(Character valute) {
        this.valute = valute;
    }
}

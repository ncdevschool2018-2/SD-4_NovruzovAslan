package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleViewModel {

    private Long id;
    private String name;

    public RoleViewModel() {}

    public RoleViewModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleViewModel(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

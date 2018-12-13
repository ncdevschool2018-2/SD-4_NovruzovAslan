package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RateViewModel {

    private Long id;
    private ProductViewModel product;
//    @JsonIgnore
    private UserViewModel user;
    private Integer rate;
    private Date date;

    public RateViewModel() {
    }

    public RateViewModel(Long id, ProductViewModel product, UserViewModel user, Integer rate, Date date) {
        this.id = id;
        this.product = product;
        this.user = user;
        this.rate = rate;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductViewModel getProduct() {
        return product;
    }

    public void setProduct(ProductViewModel product) {
        this.product = product;
    }

    public UserViewModel getUser() {
        return user;
    }

    public void setUser(UserViewModel user) {
        this.user = user;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

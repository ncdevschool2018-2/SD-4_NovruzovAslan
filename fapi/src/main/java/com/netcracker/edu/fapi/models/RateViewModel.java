package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RateViewModel {

    private Long id;
    @NotNull
    @Valid
    private ProductViewModel product;
//    @JsonIgnore
    @NotNull
    @Valid
    private UserViewModel user;
    @NotBlank
    @Size(max = 2)
    private Integer rate;
    @PastOrPresent
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

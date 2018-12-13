package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecialSubscriptionViewModel {

    private Long id;
    @JsonIgnore
    private SpecialProductViewModel product;
    @JsonIgnore
    private WalletViewModel userWallet;
    private Date start;
    private Date end;
    private Boolean active;

    public SpecialSubscriptionViewModel() {
    }

    public SpecialSubscriptionViewModel(Long id, SpecialProductViewModel product, WalletViewModel userWallet, Date start, Date end, Boolean active) {
        this.id = id;
        this.product = product;
        this.userWallet = userWallet;
        this.start = start;
        this.end = end;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SpecialProductViewModel getProduct() {
        return product;
    }

    public void setProduct(SpecialProductViewModel product) {
        this.product = product;
    }

    public WalletViewModel getUserWallet() {
        return userWallet;
    }

    public void setUserWallet(WalletViewModel userWallet) {
        this.userWallet = userWallet;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}

package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionViewModel {

    private Long id;
    @NotNull
    @Valid
    private ProductViewModel product;
    @NotNull
    @Valid
    private WalletViewModel userWallet;
    @FutureOrPresent
    private Date start;
    @FutureOrPresent
    private Date end;
    private Boolean active;

    public SubscriptionViewModel() {
    }

    public SubscriptionViewModel(Long id, ProductViewModel product, WalletViewModel userWallet, Date start, Date end, Boolean active) {
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

    public ProductViewModel getProduct() {
        return product;
    }

    public void setProduct(ProductViewModel product) {
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

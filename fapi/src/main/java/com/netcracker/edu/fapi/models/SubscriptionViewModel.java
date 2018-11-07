package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionViewModel {

    private int id;
    @JsonIgnore
    private ProductViewModel product;
    @JsonIgnore
    private UserViewModel user;
    private int duration;
    private Date start;
    private Date end;
    private boolean active;
    @JsonIgnore
    private WalletViewModel wallet;

    public SubscriptionViewModel() {
    }

    public SubscriptionViewModel(int id, ProductViewModel product, UserViewModel user, int duration, Date start, Date end, boolean active, WalletViewModel wallet) {
        this.id = id;
        this.product = product;
        this.user = user;
        this.duration = duration;
        this.start = start;
        this.end = end;
        this.active = active;
        this.wallet = wallet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public WalletViewModel getWallet() {
        return wallet;
    }

    public void setWallet(WalletViewModel wallet) {
        this.wallet = wallet;
    }
}

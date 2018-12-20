package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "specialsubscription")
public class SpecialSubscription implements Subscr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne//(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "specprod_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SpecialProduct specialProduct;
    @ManyToOne//(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_wallet_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Wallet userWallet;
    @Column(name = "start")
    private Date start;
    @Column(name = "end")
    private Date end;
    @Column(name = "is_active")
    private Boolean isActive;


    public SpecialSubscription(SpecialProduct specialProduct, Wallet userWallet, Date end, Date start, Boolean isActive) {
        this.specialProduct = specialProduct;
        this.userWallet = userWallet;
        this.end = end;
        this.start = start;
        this.isActive = isActive;
    }

    public SpecialSubscription() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SpecialProduct getProduct() {
        return specialProduct;
    }

    public void setProduct(SpecialProduct specialProduct) {
        this.specialProduct = specialProduct;
    }

    public Wallet getUserWallet() {
        return userWallet;
    }

    public void setUserWallet(Wallet userWallet) {
        this.userWallet = userWallet;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public Wallet getWallet() {
        return null;// this.getProduct().getProduct().getWallet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpecialSubscription)) return false;
        SpecialSubscription that = (SpecialSubscription) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(specialProduct, that.specialProduct) &&
                Objects.equals(userWallet, that.userWallet) &&
                Objects.equals(end, that.end) &&
                Objects.equals(start, that.start) &&
                Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, specialProduct, userWallet, end, start, isActive);
    }

    @Override
    public String toString() {
        return "SpecialSubscription{" +
                "id=" + id +
                ", specialProduct=" + specialProduct +
                ", userWallet=" + userWallet +
                ", start=" + start +
                ", end=" + end +
                ", isActive=" + isActive +
                '}';
    }
}

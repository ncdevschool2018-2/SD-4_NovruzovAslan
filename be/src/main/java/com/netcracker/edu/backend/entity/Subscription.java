package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "prod_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "start")
    private Date start;
    @Column(name = "active")
    private boolean active;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wallet_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Wallet wallet;

    public Subscription(Product product, User user, Integer duration, Date start, boolean active, Wallet wallet) {
        this.product = product;
        this.user = user;
        this.duration = duration;
        this.start = start;
        this.active = active;
        this.wallet = wallet;
    }

    public Subscription() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscription)) return false;
        Subscription that = (Subscription) o;
        return id == that.id &&
                duration == that.duration &&
                active == that.active &&
                Objects.equals(product, that.product) &&
                Objects.equals(user, that.user) &&
                Objects.equals(start, that.start) &&
                Objects.equals(wallet, that.wallet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, user, duration, start, active, wallet);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", product=" + product +
                ", user=" + user +
                ", duration=" + duration +
                ", start=" + start +
                ", active=" + active +
                ", wallet=" + wallet +
                '}';
    }

}

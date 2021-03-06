package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "rate")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne//(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "prod_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private Product product;
    @ManyToOne//(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private User user;
    @Column(name = "rate")
    private Integer rate;
    @Basic
    @Column(name = "date")
    private Date date;

    public Rate(Product product, User user, Integer rate, Date date) {
        this.product = product;
        this.user = user;
        this.rate = rate;
        this.date = date;
    }

    public Rate() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rate)) return false;
        Rate rate1 = (Rate) o;
        return id == rate1.id &&
                rate == rate1.rate &&
                Objects.equals(product, rate1.product) &&
                Objects.equals(user, rate1.user) &&
                Objects.equals(date, rate1.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, user, rate, date);
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", product=" + product +
                ", user=" + user +
                ", rate=" + rate +
                ", date=" + date +
                '}';
    }

}

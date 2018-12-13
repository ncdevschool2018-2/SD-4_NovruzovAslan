package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne//(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "prod_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;
    @ManyToOne//(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
    @Column(name = "comment")
    private String comment;
    @Basic
    @Column(name = "date")
    private Date date;

    public Comment(Product product, User user, String comment, Date date) {
        this.product = product;
        this.user = user;
        this.comment = comment;
        this.date = date;
    }

    public Comment() {

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        if (!(o instanceof Comment)) return false;
        Comment comment1 = (Comment) o;
        return id == comment1.id &&
                Objects.equals(product, comment1.product) &&
                Objects.equals(user, comment1.user) &&
                Objects.equals(comment, comment1.comment) &&
                Objects.equals(date, comment1.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, user, comment, date);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", product=" + product +
                ", user=" + user +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }
}

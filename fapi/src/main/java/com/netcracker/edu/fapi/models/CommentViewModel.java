package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentViewModel {

    private Long id;
    @NotNull
    @Valid
//    @JsonIgnore
    private ProductViewModel product;
    @NotNull
    @Valid
//    @JsonIgnore
    private UserViewModel user;
    @NotBlank
    private String comment;
    @PastOrPresent
    private Date date;

    public CommentViewModel() {
    }

    public CommentViewModel(Long id, ProductViewModel product, UserViewModel user, String comment, Date date) {
        this.id = id;
        this.product = product;
        this.user = user;
        this.comment = comment;
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
}

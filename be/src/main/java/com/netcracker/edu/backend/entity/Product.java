package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String short_description;
    private String full_description;
    private String img_src;
    private int cost;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

    public Product(String name, String short_description, String full_description, String img_src, int cost, Category category) {
        this.name = name;
        this.short_description = short_description;
        this.full_description = full_description;
        this.img_src = img_src;
        this.cost = cost;
        this.category = category;
    }

    public Product() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getFull_description() {
        return full_description;
    }

    public void setFull_description(String full_description) {
        this.full_description = full_description;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id &&
                cost == product.cost &&
                category == product.category &&
                Objects.equals(name, product.name) &&
                Objects.equals(short_description, product.short_description) &&
                Objects.equals(full_description, product.full_description) &&
                Objects.equals(img_src, product.img_src);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, short_description, full_description, img_src, cost, category);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", short_description='" + short_description + '\'' +
                ", full_description='" + full_description + '\'' +
                ", img_src='" + img_src + '\'' +
                ", cost=" + cost +
                ", category=" + category +
                '}';
    }

}

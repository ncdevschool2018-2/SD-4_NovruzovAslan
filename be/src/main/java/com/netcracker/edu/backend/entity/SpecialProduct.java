package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "specialproduct")
public class SpecialProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(name = "name")  // todo: fix table in database
//    private String name;
//    @Column(name = "description")
//    private String description;
//    @Column(name = "img_src")
//    private String img_src;
    @Column(name = "cost")
    private Double cost;
//    @ManyToOne
//    @JoinColumn(name = "wallet_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private Wallet wallet;

    //    @ManyToOne//(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "prodId", nullable = false)
//    @JsonIgnore
//    private Product product;
//    @ManyToMany(
//            cascade = {
//                CascadeType.PERSIST,
//                CascadeType.MERGE
//            },
//            mappedBy = "specialProducts")
////    @JsonBackReference
//    @JsonIgnore
//    private Set<Product> products = new HashSet<>();


    public SpecialProduct(Product product, Double cost) {
//        this.product = product;
        this.cost = cost;
//        this.products = products;
    }

    public SpecialProduct() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

//    public Product getProduct() {
//        return product;
//    }

//    public void setProduct(Product product) {
//        this.product = product;
//    }

//    public Set<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(Set<Product> products) {
//        this.products = products;
//    }

    @Override
    public String toString() {
        return "SpecialProduct{" +
                "id=" + id +
//                ", product=" + product +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpecialProduct)) return false;
        SpecialProduct that = (SpecialProduct) o;
        return id == that.id &&
                cost == that.cost;// &&
//                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost);
    }
}

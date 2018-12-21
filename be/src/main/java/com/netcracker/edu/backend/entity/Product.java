package com.netcracker.edu.backend.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "cost")
    private Double cost;

    @ManyToOne//(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private Category category;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Wallet wallet;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "product_specialproduct",
            joinColumns = @JoinColumn(name = "prod_id"),
            inverseJoinColumns = @JoinColumn(name = "specprod_id")
    )
    private Set<SpecialProduct> specialProducts = new HashSet<>();

    public Product(String name, String description, Double cost, Category category, Wallet wallet, Set<SpecialProduct> specialProducts) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.category = category;
        this.wallet = wallet;
        this.specialProducts = specialProducts;
    }

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Set<SpecialProduct> getSpecialProducts() {
        return specialProducts;
    }

    public void setSpecialProducts(Set<SpecialProduct> specialProducts) {
        this.specialProducts = specialProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id &&
                cost == product.cost &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(category, product.category) &&
                Objects.equals(wallet, product.wallet) &&
                Objects.equals(specialProducts, product.specialProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, cost, category, wallet, specialProducts);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", category=" + category +
                ", wallet=" + wallet +
                ", specialProducts=" + specialProducts +
                '}';
    }
}

package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecialproductViewModel {

    private int id;
    @JsonIgnore
    private ProductViewModel product;
    private int cost;
    private Set<ProductViewModel> products = new HashSet<>();

    public SpecialproductViewModel() {
    }

    public SpecialproductViewModel(int id, ProductViewModel product, int cost, Set<ProductViewModel> products) {
        this.id = id;
        this.product = product;
        this.cost = cost;
        this.products = products;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Set<ProductViewModel> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductViewModel> products) {
        this.products = products;
    }

}

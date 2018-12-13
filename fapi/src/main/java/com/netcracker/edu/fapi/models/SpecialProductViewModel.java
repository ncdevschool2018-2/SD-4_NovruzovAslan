package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecialProductViewModel {

    private Long id;
//    @JsonIgnore
//    private ProductViewModel product;
    private Double cost;
//    @JsonIgnore
//    private Set<ProductViewModel> products = new HashSet<>();

    public SpecialProductViewModel() {
    }

    public SpecialProductViewModel(Long id, Double cost) {
        this.id = id;
//        this.product = product;
        this.cost = cost;
//        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public ProductViewModel getProduct() {
//        return product;
//    }
//
//    public void setProduct(ProductViewModel product) {
//        this.product = product;
//    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

//    public Set<ProductViewModel> getProducts() {
//        return products;
//    }

//    public void setProducts(Set<ProductViewModel> products) {
//        this.products = products;
//    }

}

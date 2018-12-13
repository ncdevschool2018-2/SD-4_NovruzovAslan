package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductViewModel {

    private Long id;
    private String name;
    private String description;
    private String img_src;
    private Double cost;
//    @JsonIgnore
    private CategoryViewModel category;
//    @JsonIgnore
    private WalletViewModel wallet;
    private Set<SpecialProductViewModel> specialProducts = new HashSet<>();

    public ProductViewModel() {
    }

    public ProductViewModel(Long id, String name, String description, String img_src, Double cost, CategoryViewModel category, WalletViewModel wallet, Set<SpecialProductViewModel> specialProducts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img_src = img_src;
        this.cost = cost;
        this.category = category;
        this.wallet = wallet;
        this.specialProducts = specialProducts;
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

    public WalletViewModel getWallet() {
        return wallet;
    }

    public void setWallet(WalletViewModel wallet) {
        this.wallet = wallet;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public CategoryViewModel getCategory() {
        return category;
    }

    public void setCategory(CategoryViewModel category) {
        this.category = category;
    }

    public Set<SpecialProductViewModel> getSpecialProducts() {
        return specialProducts;
    }

    public void setSpecialProducts(Set<SpecialProductViewModel> specialProducts) {
        this.specialProducts = specialProducts;
    }

}

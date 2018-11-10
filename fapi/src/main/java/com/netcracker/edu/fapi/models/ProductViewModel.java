package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductViewModel {

    private Long id;
    private String name;
    private String short_description;
    private String full_description;
    private String img_src;
    private Integer cost;
    @JsonIgnore
    private CategoryViewModel category;
    private Set<SpecialProductViewModel> specialproducts = new HashSet<>();

    public ProductViewModel() {
    }

    public ProductViewModel(Long id, String name, String short_description, String full_description, String img_src, Integer cost, CategoryViewModel category, Set<SpecialProductViewModel> specialproducts) {
        this.id = id;
        this.name = name;
        this.short_description = short_description;
        this.full_description = full_description;
        this.img_src = img_src;
        this.cost = cost;
        this.category = category;
        this.specialproducts = specialproducts;
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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public CategoryViewModel getCategory() {
        return category;
    }

    public void setCategory(CategoryViewModel category) {
        this.category = category;
    }

    public Set<SpecialProductViewModel> getSpecialproducts() {
        return specialproducts;
    }

    public void setSpecialproducts(Set<SpecialProductViewModel> specialproducts) {
        this.specialproducts = specialproducts;
    }

}

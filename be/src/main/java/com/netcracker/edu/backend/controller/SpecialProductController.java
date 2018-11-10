package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.SpecialProduct;
import com.netcracker.edu.backend.service.SpecialProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/specialproducts")
public class SpecialProductController {

    private SpecialProductService specialProductService;

    @Autowired
    public SpecialProductController(SpecialProductService specialProductService) {
        this.specialProductService = specialProductService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SpecialProduct> getSpecialProductById(@PathVariable(name = "id") Long id) {
        Optional<SpecialProduct> specialProduct = specialProductService.getSpecialProductById(id);
        if (specialProduct.isPresent()) {
            return ResponseEntity.ok(specialProduct.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<SpecialProduct> getAllSpecialProducts() {
        return specialProductService.getAllSpecialProducts();
    }

    @RequestMapping(method = RequestMethod.POST)
    public SpecialProduct saveSpecialProduct(@RequestBody SpecialProduct account) {
        return specialProductService.saveSpecialProduct(account);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSpecialProduct(@PathVariable(name = "id") Long id) {
        specialProductService.deleteSpecialProduct(id);
        return ResponseEntity.noContent().build();
    }

}

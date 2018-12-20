package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.SpecialProductViewModel;
import com.netcracker.edu.fapi.service.SpecialProductDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/special-products")
public class SpecialProductDataController {

    @Autowired
    private SpecialProductDataService specialProductDataService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<SpecialProductViewModel>> getAllSpecialProducts() {
        return ResponseEntity.ok(specialProductDataService.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SpecialProductViewModel> getSpecialProductById(@PathVariable(name="id") String id) {
        return ResponseEntity.ok(specialProductDataService.getSpecialProductById(Long.valueOf(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SpecialProductViewModel> saveSpecialProduct(@RequestBody SpecialProductViewModel specialProduct /*todo server validation*/) {
        if (specialProduct != null) {
            return ResponseEntity.ok(specialProductDataService.saveSpecialProduct(specialProduct));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSpecialProduct(@PathVariable String id) {
        specialProductDataService.deleteSpecialProduct(Long.valueOf(id));
    }

}


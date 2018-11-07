package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Specialproduct;
import com.netcracker.edu.backend.service.SpecialproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/specialproducts")
public class SpecialproductController {

    private SpecialproductService specialproductService;

    @Autowired
    public SpecialproductController(SpecialproductService specialproductService) {
        this.specialproductService = specialproductService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Specialproduct> getSpecialproductById(@PathVariable(name = "id") Long id) {
        Optional<Specialproduct> specialproduct = specialproductService.getSpecialproductById(id);
        if (specialproduct.isPresent()) {
            return ResponseEntity.ok(specialproduct.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Specialproduct> getAllSpecialproducts() {
        return specialproductService.getAllSpecialproducts();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Specialproduct saveSpecialproduct(@RequestBody Specialproduct account) {
        return specialproductService.saveSpecialproduct(account);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSpecialproduct(@PathVariable(name = "id") Long id) {
        specialproductService.deleteSpecialproduct(id);
        return ResponseEntity.noContent().build();
    }

}

package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.SpecialproductViewModel;
import com.netcracker.edu.fapi.service.SpecialproductDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialproducts")
public class SpecialproductDataController {

    @Autowired
    private SpecialproductDataService specialproductDataService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<SpecialproductViewModel>> getAllSpecialproducts() {
        return ResponseEntity.ok(specialproductDataService.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SpecialproductViewModel> getSpecialproductById(@PathVariable(name="id") String id) {
        return ResponseEntity.ok(specialproductDataService.getSpecialproductById(Long.valueOf(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SpecialproductViewModel> saveSpecialproduct(@RequestBody SpecialproductViewModel specialproduct /*todo server validation*/) {
        if (specialproduct != null) {
            return ResponseEntity.ok(specialproductDataService.saveSpecialproduct(specialproduct));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSpecialproduct(@PathVariable String id) {
        specialproductDataService.deleteSpecialproduct(Long.valueOf(id));
    }

}


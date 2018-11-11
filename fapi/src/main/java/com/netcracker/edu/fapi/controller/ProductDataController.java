package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.CategoryViewModel;
import com.netcracker.edu.fapi.models.ProductViewModel;
import com.netcracker.edu.fapi.service.ProductDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductDataController {

    @Autowired
    private ProductDataService productDataService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<ProductViewModel>> getAllProducts() {
        return ResponseEntity.ok(productDataService.getAll());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<ProductViewModel>> getProductsByCategoryId(@RequestParam(name = "category_id") Long id) {
        return ResponseEntity.ok(productDataService.getProductsByCategoryId(id));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductViewModel> getProductById(@PathVariable(name="id") String id) {
        return ResponseEntity.ok(productDataService.getProductById(Long.valueOf(id)));
    }

    @RequestMapping(value = "/{category_id}",method = RequestMethod.POST)
    public ResponseEntity<ProductViewModel> saveProduct(@RequestBody ProductViewModel product, @PathVariable(name = "category_id") long category_id /*todo server validation*/) {
        if (product != null) {
            product.setCategory(new CategoryViewModel(category_id, ""));
            return ResponseEntity.ok(productDataService.saveProduct(product));
        }
        return null;
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<ProductViewModel> saveProduct(@RequestBody ProductViewModel product /*todo server validation*/) {
//        if (product != null) {
//            return ResponseEntity.ok(productDataService.saveProduct(product));
//        }
//        return null;
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable String id) {
        productDataService.deleteProduct(Long.valueOf(id));
    }

}


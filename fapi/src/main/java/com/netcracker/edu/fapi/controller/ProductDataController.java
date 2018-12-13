package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.CategoryViewModel;
import com.netcracker.edu.fapi.models.ProductViewModel;
//import com.netcracker.edu.fapi.models.RestPageImpl;
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

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public ResponseEntity<List<ProductViewModel>> getAllProducts() {
//        return ResponseEntity.ok(productDataService.getAll());
//    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<ProductViewModel>> getProducts(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "category_id", required = false) Long category_id) {
        return ResponseEntity.ok(productDataService.getPage(page, category_id));
    }

    @RequestMapping(value = "/total-pages", method = RequestMethod.GET)
    public ResponseEntity<Integer> getTotalPages(
            @RequestParam(name = "category_id", required = false) Long category_id) {
        return ResponseEntity.ok(productDataService.getTotalPages(category_id));
    }

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public ResponseEntity<List<ProductViewModel>> getProducts(@RequestParam(name = "category_id", required = false) Long id) {
//        if(id == null)
//            return ResponseEntity.ok(productDataService.getPage());
//        return ResponseEntity.ok(productDataService.getPage());
//    }

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

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public RestPageImpl<ProductViewModel> getPage(HttpServletRequest request) {
//        return productDataService.getPage(request);
//    }

}


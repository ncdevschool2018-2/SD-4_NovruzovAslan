package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Category;
import com.netcracker.edu.backend.entity.Product;
import com.netcracker.edu.backend.repository.ProductRepository;
import com.netcracker.edu.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProductById(@PathVariable(name = "id") Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/{category_id}",method = RequestMethod.POST)
    public Product saveProduct(@RequestBody Product product, @PathVariable(name = "category_id") Long category_id) {
        product.setCategory(new Category(category_id, ""));
        return productService.saveProduct(product);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Product> getProductsByCategoryId(@RequestParam(name = "category_id") long id) {
        return productService.getProductsByCategoryId(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }


//    @RequestMapping(method = RequestMethod.POST)
//    public Product saveProduct(@RequestBody Product product) {
//        return productService.saveProduct(product);
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}

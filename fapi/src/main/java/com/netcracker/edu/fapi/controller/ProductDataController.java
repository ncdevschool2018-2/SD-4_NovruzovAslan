package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.CategoryViewModel;
import com.netcracker.edu.fapi.models.ProductViewModel;
//import com.netcracker.edu.fapi.models.RestPageImpl;
import com.netcracker.edu.fapi.service.ProductDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductDataController {

    @Autowired
    private ProductDataService productDataService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<ProductViewModel>> getProducts(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "category_id", required = false) Long category_id) {
        return ResponseEntity.ok(productDataService.getPage(page, size, category_id));
    }

    @RequestMapping(value = "/own", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('admin') or hasAuthority('content_manager')")
    public ResponseEntity<List<ProductViewModel>> getOwnProducts(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "manager_id") Long manager_id) {
        return ResponseEntity.ok(productDataService.getOwnPage(page, size, manager_id));
    }

    @RequestMapping(value = "/total-pages", method = RequestMethod.GET)
    public ResponseEntity<Integer> getTotalPages(
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "category_id", required = false) Long category_id,
            @RequestParam(name = "manager_id", required = false) Long manager_id) {
        return ResponseEntity.ok(productDataService.getTotalPages(size, category_id, manager_id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductViewModel> getProductById(@PathVariable(name="id") String id) {
        return ResponseEntity.ok(productDataService.getProductById(Long.valueOf(id)));
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('admin') or hasAuthority('content_manager')")
    public ResponseEntity<ProductViewModel> saveProduct(@RequestBody ProductViewModel product) {
        if (product != null) {
            return ResponseEntity.ok(productDataService.saveProduct(product));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('admin')")
    public void deleteProduct(@PathVariable String id) {
        productDataService.deleteProduct(Long.valueOf(id));
    }

//    @RequestMapping(value = "", method = RequestMethod.DELETE)
//    @PreAuthorize("hasAuthority('content_manager')")
//    public void deleteOwnProduct(@RequestParam(name = "product_id") String prodId, @RequestParam(name = "user_id") String userId) {
//        productDataService.deleteProduct(Long.valueOf(prodId));
//    }

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public RestPageImpl<ProductViewModel> getPage(HttpServletRequest request) {
//        return productDataService.getPage(request);
//    }

    //    @RequestMapping(value = "/{category_id}",method = RequestMethod.POST)
//    @PreAuthorize("hasAuthority('admin') or hasAuthority('content_manager')")
//    public ResponseEntity<ProductViewModel> saveProduct(@RequestBody ProductViewModel product, @PathVariable(name = "category_id") long category_id /*todo server validation*/) {
//        if (product != null) {
//            product.setCategory(new CategoryViewModel(category_id, ""));
//            return ResponseEntity.ok(productDataService.saveProduct(product));
//        }
//        return null;
//    }


    //    @RequestMapping(value = "", method = RequestMethod.GET)
//    public ResponseEntity<List<ProductViewModel>> getAllProducts() {
//        return ResponseEntity.ok(productDataService.getAll());
//    }

//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<ProductViewModel> saveProduct(@RequestBody ProductViewModel product /*todo server validation*/) {
//        if (product != null) {
//            return ResponseEntity.ok(productDataService.saveProduct(product));
//        }
//        return null;
//    }

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public ResponseEntity<List<ProductViewModel>> getProducts(@RequestParam(name = "category_id", required = false) Long id) {
//        if(id == null)
//            return ResponseEntity.ok(productDataService.getPage());
//        return ResponseEntity.ok(productDataService.getPage());
//    }
}


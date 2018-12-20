package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.ProductViewModel;
//import com.netcracker.edu.fapi.models.RestPageImpl;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProductDataService {
    List<ProductViewModel> getAll();
//    List<ProductViewModel> getProductsByCategoryId(Long id);
    ProductViewModel getProductById(Long id);
    ProductViewModel saveProduct(ProductViewModel product);
    void deleteProduct(Long id);
    List<ProductViewModel> getPage(Integer page, Integer size, Long category_id);
    List<ProductViewModel> getOwnPage(Integer page, Integer size, Long manager_id);
    Integer getTotalPages(Integer size, Long category_id, Long manager_id);
}

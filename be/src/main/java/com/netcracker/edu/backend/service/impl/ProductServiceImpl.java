package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Product;
import com.netcracker.edu.backend.entity.Wallet;
import com.netcracker.edu.backend.repository.ProductRepository;
import com.netcracker.edu.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product saveProduct(Product product) {
        product.setImg_src("path");
//        product.setWallet(new Wallet(2L));
        if(product.getId()==null && repository.findProductByName(product.getName()).isPresent())
            return null;
        return repository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return repository.findAll();
    }

//    @Override
//    public Iterable<Product> getProductsByCategoryId(Long id) {
//        return repository.findProductsByCategoryId(id);
//    }

    @Override
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Product> getPage(Integer page, Integer size, Long category_id) {
//        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"));
        Pageable pageable = new PageRequest(page-1, size, new Sort(Sort.Direction.ASC, "id"));
        if(category_id==null)
            return repository.findAll(pageable);
        return repository.findProductsByCategoryId(pageable, category_id);
    }

    @Override
    public Page<Product> getOwnPage(Integer page, Integer size, Long manager_id) {
        Pageable pageable = new PageRequest(page-1, size, new Sort(Sort.Direction.ASC, "id"));
        return repository.findProductsByWalletUserId(pageable, manager_id);
    }

}

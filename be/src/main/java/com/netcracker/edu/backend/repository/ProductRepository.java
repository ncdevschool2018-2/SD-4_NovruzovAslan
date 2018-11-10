package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findProductsByCategoryId(Long id);
}

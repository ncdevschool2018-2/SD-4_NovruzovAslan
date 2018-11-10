package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.SpecialProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialProductRepository extends CrudRepository<SpecialProduct, Long> {
}

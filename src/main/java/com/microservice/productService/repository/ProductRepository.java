package com.microservice.productService.repository;

import com.microservice.productService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Product findByProductName(String key);
}

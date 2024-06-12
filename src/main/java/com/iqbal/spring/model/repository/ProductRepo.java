package com.iqbal.spring.model.repository;

import com.iqbal.spring.model.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long> {

    // Custom Query
    List<Product> findByNameContains(String name);
}

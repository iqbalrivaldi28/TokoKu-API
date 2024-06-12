package com.iqbal.spring.model.repository;

import com.iqbal.spring.model.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> {

}

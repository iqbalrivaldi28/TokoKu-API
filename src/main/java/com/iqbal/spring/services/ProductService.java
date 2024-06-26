package com.iqbal.spring.services;

import com.iqbal.spring.model.entity.Product;
import com.iqbal.spring.model.entity.Suplier;
import com.iqbal.spring.model.repository.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product save(Product product){
        return productRepo.save(product);
    }

    public Product findOne(Long id){
        Optional<Product> product = productRepo.findById(id);
        if (!product.isPresent()){
            return null;
        }
        return product.get();
    }

    public Iterable<Product> findAll(){
        return productRepo.findAll();
    }

    public void removeOne(Long id){
        productRepo.deleteById(id);
    }

    // Custom query
    public List<Product> findByName(String name){
        return productRepo.findByNameContains(name);
    }

    // Untuk nampilin suplier
    public void addSuplier(Suplier suplier, Long productId){
        Product product = findOne(productId);

        if (product == null){
            throw new RuntimeException("Product with ID: " + productId + " Not Found");
        }

        product.getSupliers().add(suplier);
        save(product);
    }
}

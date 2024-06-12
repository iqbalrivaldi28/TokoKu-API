package com.iqbal.spring.controllers;

import com.iqbal.spring.model.entity.Product;
import com.iqbal.spring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductContoller {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product create(@RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping
    public Iterable<Product> findAll(){
        return  productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable ("id") Long id){
        return productService.findOne(id);
    }

    @PutMapping
    public Product update(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        productService.removeOne(id);
    }

    // Custom Query for endPoint
    @GetMapping("/search")
    public List<Product> findByName(@RequestParam("name") String name){
        return productService.findByName(name);
    }

}

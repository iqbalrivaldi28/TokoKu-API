package com.iqbal.spring.controllers;

import com.iqbal.spring.dto.ResponseData;
import com.iqbal.spring.model.entity.Product;
import com.iqbal.spring.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductContoller {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors){

        ResponseData<Product> responseData = new ResponseData<>();

        // Kalau error data tidak sesuai
        if (errors.hasErrors()){
            for(ObjectError error: errors.getAllErrors()){
               responseData.getMessage().add(error.getDefaultMessage());
            }

            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        // Kalau data sesuai langsung kita return datanya
        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.ok(responseData);
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

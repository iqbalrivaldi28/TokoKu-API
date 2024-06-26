package com.iqbal.spring.controllers;

import com.iqbal.spring.dto.CategoryDTO;
import com.iqbal.spring.dto.ResponseData;
import com.iqbal.spring.model.entity.Category;
import com.iqbal.spring.services.CategoryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<ResponseData<Category>> create (@Valid @RequestBody CategoryDTO categoryDTO, Errors errors){
        ResponseData<Category> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }

            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Category category = modelMapper.map(categoryDTO, Category.class);

        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok(responseData);
    }


    @GetMapping
    public Iterable<Category> findAll(){
        return  categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findOne(@PathVariable("id") Long id){
        return categoryService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public  void removeOve(@PathVariable("id") Long id){
        categoryService.removeOne(id);
    }

    @PutMapping
    ResponseEntity<ResponseData<Category>> update (@Valid @RequestBody CategoryDTO categoryDTO, Errors errors){
        ResponseData<Category> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }

            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Category category = modelMapper.map(categoryDTO, Category.class);

        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok(responseData);
    }


}

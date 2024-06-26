package com.iqbal.spring.services;

import com.iqbal.spring.model.repository.CategoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;


}

package com.example.demo.controller;

import com.example.demo.service.IProductCategoryService;
import com.example.demo.service.dto.ProductCategoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product-categories")
@Slf4j
public class ProductCategoryController {

    @Autowired
    IProductCategoryService iProductCategoryService;
    @PostMapping("/create")
    public ProductCategoryDTO save(ProductCategoryDTO productCategoryDTO){
        return iProductCategoryService.save(productCategoryDTO);

    }

}

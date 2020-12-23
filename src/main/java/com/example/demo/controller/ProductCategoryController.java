package com.example.demo.controller;

import com.example.demo.service.IProductCategoryService;
import com.example.demo.service.dto.ProductCategoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-categories")
@Slf4j
public class ProductCategoryController {

    @Autowired
    IProductCategoryService iProductCategoryService;
    @PostMapping("/create")
    public ProductCategoryDTO save(@RequestBody ProductCategoryDTO productCategoryDTO){
        return iProductCategoryService.save(productCategoryDTO);
    }
    @PutMapping("/update/{id}")
    public ProductCategoryDTO update(@RequestBody ProductCategoryDTO productCategoryDTO,@PathVariable Long id){
        return iProductCategoryService.update(productCategoryDTO,id);
    }
    @GetMapping("/find-one/{id}")
    public ProductCategoryDTO findone(@PathVariable Long id){
        return iProductCategoryService.findById(id).get();
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        iProductCategoryService.delete(id);
    }

}

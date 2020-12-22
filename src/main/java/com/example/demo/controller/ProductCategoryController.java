package com.example.demo.controller;

import com.example.demo.service.IProductCategoryService;
import com.example.demo.service.dto.ProductCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/product-categories")
public class ProductCategoryController {
    @Autowired
    @Qualifier("productCategoryServiceImpl")
    private IProductCategoryService productCategoryService;

    @PostMapping(value = "/create")
    @Transactional
    public ProductCategoryDTO create(@RequestBody ProductCategoryDTO productCategoryDTO) {
        return productCategoryService.save(productCategoryDTO);
    }

    @GetMapping(value = "/get-all")
    public List<ProductCategoryDTO> findAll(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size
    ) {
        return productCategoryService.findAll(page, size);
    }

    @GetMapping(value = "/get-one")
    public ProductCategoryDTO findById(@RequestParam Long id) {
        return productCategoryService.findById(id);
    }

    @PutMapping(value = "/update")
    @Transactional
    public ProductCategoryDTO update(@RequestParam Long id, @RequestBody ProductCategoryDTO productCategoryDTO) {
        return productCategoryService.update(id, productCategoryDTO);
    }

    @DeleteMapping(value = "/delete")
    public void delete(@RequestParam Long id) {
        productCategoryService.deleteById(id);
    }
}
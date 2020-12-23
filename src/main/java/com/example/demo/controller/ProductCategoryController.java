package com.example.demo.controller;

import com.example.demo.service.IProductCategoryService;
import com.example.demo.service.dto.ProductCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product-categories")
public class ProductCategoryController {
    @Autowired
    @Qualifier("productCategoryServiceImpl")
    private IProductCategoryService productCategoryService;

    @PostMapping(value = "/create")
    @Transactional
    public ResponseEntity<ProductCategoryDTO> create(@Valid @RequestBody ProductCategoryDTO productCategoryDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productCategoryService.save(productCategoryDTO));
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<List<ProductCategoryDTO>> findAll(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productCategoryService.findAll(page, size));
    }

    @GetMapping(value = "/get-one")
    public ResponseEntity<ProductCategoryDTO> findById(@RequestParam Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productCategoryService.findById(id));
    }

    @PutMapping(value = "/update")
    @Transactional
    public ResponseEntity<ProductCategoryDTO> update(@RequestParam Long id, @Valid @RequestBody ProductCategoryDTO productCategoryDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productCategoryService.update(id, productCategoryDTO));
    }

    @DeleteMapping(value = "/delete")
    public void delete(@RequestParam Long id) {
        productCategoryService.deleteById(id);
    }
}
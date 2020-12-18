package com.example.demo.controller;


import com.example.demo.service.IProductService;
import com.example.demo.service.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

    @Autowired
    IProductService productService;

    @PostMapping(value = "/create")
    public ProductDTO create(@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @GetMapping("/get-all-product")
    public List<ProductDTO> findall() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        return null;
    }

    @GetMapping("/get-one-product/{id}")
    public ProductDTO findOne(@PathVariable Long id) {
        return productService.findById(id).get();
    }

    @PutMapping("/update-product/{id}")
    public ProductDTO update(@RequestBody ProductDTO productDTO, @PathVariable Long id) {
        return productService.update(productDTO, id);
    }

    @DeleteMapping(value = "/delete-product/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}

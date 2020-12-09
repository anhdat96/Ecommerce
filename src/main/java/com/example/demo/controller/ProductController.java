package com.example.demo.controller;

import com.example.demo.models.Products;
import com.example.demo.service.dto.ProductDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @GetMapping(value = "/products")
    public List<ProductDTO> getAllProducts(){
        return null;

    }
}

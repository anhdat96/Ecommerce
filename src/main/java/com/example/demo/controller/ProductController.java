package com.example.demo.controller;

import com.example.demo.models.Products;
import com.example.demo.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    @GetMapping(value = "/all_Products")
    public List<Products> findAll() {
        return productServiceImpl.finAll();
    }

    @PostMapping(value = "/create")
    public Products create(@RequestBody Products products) {
        return productServiceImpl.save(products);
    }

    @GetMapping(value = "/get-one-product/{id}")
    public Products findById(@PathVariable Long id) {
        Optional<Products> optionalProducts = productServiceImpl.findById(id);
        if (!optionalProducts.isPresent()) {
            log.error("ID " + id + "is not exist");
            ResponseEntity.badRequest().build();
        }
        return optionalProducts.get();
    }

    @PutMapping(value = "/update/{id}")
    public Products update(@PathVariable Long id, @RequestBody Products products) {
        if (!productServiceImpl.findById(id).isPresent()) {
            log.error("ID " + id + "is not exist");
        }
        return productServiceImpl.save(products);
    }

    @DeleteMapping(value = "/delete-product/{id}")
    public void delete(@PathVariable Long id) {
        if (!productServiceImpl.findById(id).isPresent()) {
            log.error("ID " + id + "is not exist");
            ResponseEntity.badRequest().build();
        }
        productServiceImpl.deleteById(id);

    }
}
